package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 생성자에서 this.discountPolicy = discountPolicy; 이 구문이 없다면 컴파일 오류가 발생한다. 필드 값에서 이미 final이라고 선언을 했기 떄문인다.
 * 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 final 키워드를 사용할 수 없다. 오직 생성자 주입 방식만 final 키워드를 사용할 수 있다
 */

@Component
//롬복 라이브러리가 제공하는 @RequiredArgsConstructor 기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다. (다음 코드에는 보이지 않지만 실제 호출 가능하다.)
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 현재 RateDiscountPolicy,FixDiscountPolicy 두 개를 @Componenet를 해놓았기 때문에 OrderServiceImpl.java 생성자 부분에서 discountPolicy에서 오류가 발생한다.
     * 이 오류를 해결할 수 있는 방법은 3가지가 있습니다. 1.@AutoWired 필드 명 매  2.@Quiifier -> @Quilifier끼리 매칭 -> 빈 이름 매  3.Primary사용
     * @param memberRepository
     * @param discountPolicy
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
