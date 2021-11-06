package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(SingletonBean.class);

        System.out.println("bean1호출");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);

        System.out.println("bean2호출");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean2 = " + singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singletonConfig.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singletonConfig.destroy");
        }
    }
}
