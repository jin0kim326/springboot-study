package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
    // AppConfig에 보면
    // @Bean MemberService -> MemberRepository();
    // @Bean OrderService -> MemberReposiory();
    // @Bean MemberRepository -> MemberRepository();

    // MemberRepository가 총 3번 new(호출) 된다.
    // => 객체가 3번 호출, 즉 3개가 생성되면 싱글톤이 깨지는게 아닌가?? 라는 의문을 가지고 테스트

    // 각 order,member구현개체에 memberRepository를 리턴하는 테스트 메소드 작성
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void ConfigurationSingletonTest() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        // 3객체 전부 동일한 인스턴스를 참고하고 있음
        // 그러면 호출로그를 확인해보자.
        // 예상되는 호출값은 MemberRepository는 3번, order,member서비스는 각각 한번이다

        // -> @@@ MemberRepository는 한번밖에 호출되지않는다!!!!!
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }


}
