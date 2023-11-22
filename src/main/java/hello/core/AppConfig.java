package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration 어노테이션을 사용하면, CGLIB라는 기술을 통해 스프링 컨테이너에 등록된 객체들의 모든 싱글톤 패턴을 보장해준다.
 * 따라서, 아래의 @Bean 중에 로직상 MemoryMemberRepository이 두번 생성이 되는데, CGLIB 기술을 통해 두번 생성이 되는걸 막아줌
 * But, 그냥 @Bean으로 config 클래스를 설정하면, CGLIB 기술이 적용되지 않는 빈객체를 생성하게 되어, 여러번 중복 호출이 됨(싱글톤 보장 x)
 * 따라서 스프링 설정정보에 관한 것은 항상 @Configuration을 사용하자
 */
@Configuration
public class AppConfig {

    // 생성자 주입
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
