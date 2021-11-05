package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class AllBeanTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

    @Test
    void findAllBean() {
        DiscountService discountService = ac.getBean(DiscountService.class);        
        Member member = new Member(1L, "jinyoung", Grade.VIP);
        int discountPrice = discountService.discount(member, 15000, "rateDiscountPolicy");

        Assertions.assertThat(discountPrice).isEqualTo(1500);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;

        // map으로 모든 DiscountPolicy 하위 구현체들 주입받기
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }


    }
}
