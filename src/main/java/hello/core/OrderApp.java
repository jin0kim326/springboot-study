package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        MemberService memberService = new MemberServiceImpl();

        Member member1 = new Member(1L,"memberA", Grade.VIP);
        Member member2 = new Member(2L,"memberB", Grade.BASIC);

        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(1L, "iPhone12", 13000);
        Order order2 = orderService.createOrder(2L, "iPhone12", 13000);

        System.out.println("order1 = " + order1);
        System.out.println("order2 = " + order2);



    }
}
