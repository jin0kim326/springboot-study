package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);


    @Test
    @DisplayName("부모타입조회시 자식이 둘이상 있으면, 중복 오류가 난다")
    void findBeanByParentType() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입조회시 자식이 둘이상 있으면, 빈 이름을 지정해준다")
    void findBeanByName() {
        DiscountPolicy bean = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위타입으로 조회")
    void findBeanByType() {
        FixDiscountPolicy bean = ac.getBean(FixDiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모두조회")
    void findBeanAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("부모 타입으로 모두조회 - object")
    void findBeanAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
        
    }

    @Configuration
    static class testConfig {

        @Bean
        DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}
