package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    @Test
    void componentFilterConfigTest() {
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
    }

    @Configuration
    @ComponentScan(
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
