package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    ApplicationContext ac =
            new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void test(){
        MemberService bean = ac.getBean(MemberService.class);
        MemberService bean2 = ac.getBean(MemberServiceImpl.class);
        System.out.println("bean = " + bean);

        Assertions.assertThat(bean2).isInstanceOf(MemberServiceImpl.class);
    }

}
