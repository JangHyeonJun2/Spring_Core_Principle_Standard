package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
//    FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
//
//    MemberService memberService = new MemberServiceImpl(memoryMemberRepository);
//    OrderService orderService = new OrderServiceImpl(memoryMemberRepository,fixDiscountPolicy);
    MemberService memberService;
    OrderService orderService;

    @BeforeEach //실행하기전에 memberService에 appConfig.memberService()를 넣어준다.
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
