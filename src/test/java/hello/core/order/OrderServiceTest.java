package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    void beforeEach( ) {
        memberService = new AppConfig().memberService();
        orderService = new AppConfig().orderService();
    }

    @Test
    void basicMemberOrder( ) {
        // given
        Long memberId = 1L;
        Member member1 = new Member(memberId,"memberA", Grade.BASIC);
        memberService.join(member1);

        //when
        Order order = orderService.createOrder(memberId, "iPhone12", 13000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(0);
    }

    @Test
    void vipMemberOrder( ) {
        // given
        Long memberId = 2L;
        Member member2 = new Member(memberId,"memberB", Grade.VIP);
        memberService.join(member2);

        //when
        Order order = orderService.createOrder(memberId, "iPhone12", 13000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1300);

    }
}
