package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeBeanTest {
    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(prototypeBean.class);

        System.out.println("prototypeBean1호출");
        prototypeBean prototypeBean1 = ac.getBean(prototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);

        System.out.println("prototypeBean2호출");
        prototypeBean prototypeBean2 = ac.getBean(prototypeBean.class);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class prototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}
