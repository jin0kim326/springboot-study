package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {
    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(PrototypeBean.class);
    @Test
    void prototypeFind() {
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(SingletonBean.class,PrototypeBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        int logic1 = singletonBean1.logic();
        Assertions.assertThat(logic1).isEqualTo(1);

        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        int logic2 = singletonBean2.logic();
        Assertions.assertThat(logic2).isEqualTo(1);

        ac.close();
    }


    @Scope("singleton")
    static class SingletonBean {
        //생성시점에 이미 주입 => logic()함수마다 생성하고싶으면? ObjectProvider사용
        // JSR330 Provider를 사용할 수도 있지만, 스프링이 제공하는 ObjectProvider를 사용하면됨
//        private PrototypeBean prototypeBean;

        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public SingletonBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}
