package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

    @Test
    void singletonWarning() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);
        System.out.println("userAPrice = " + userAPrice);
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        // 유저 A는 10,000원으로 주문했는데, 조회할때는 20,000원이 나오는 오류 발생
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }



    static class TestConfig {
        @Bean
        StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
