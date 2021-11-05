package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {
    @Test
    void constructorInjection() {

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"jinyoung",Grade.VIP));

        OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L, "iPhone", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
