package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 할인이 적용되어야함")
    void vip_o() {
        //given
        Member member = new Member(1L, "VIP", Grade.VIP);

        //when
        int price = rateDiscountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(price).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인X")
    void vip_x() {
        //given
        Member member = new Member(2L, "BASIC", Grade.BASIC);

        //when
        int price = rateDiscountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(price).isEqualTo(0);
    }

}