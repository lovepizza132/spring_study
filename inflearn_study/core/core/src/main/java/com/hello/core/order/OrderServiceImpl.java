package com.hello.core.order;

import com.hello.core.annotation.MainDiscountPolicy;
import com.hello.core.discount.DiscountPolicy;
//import com.hello.core.dicount.FixDiscountPolicy;
//import com.hello.core.dicount.RateDiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor//final이 붙은 == 필수값인 필드의 생성자를 만들어줌 (ctrl+F12 로 만들어진 생성자 확인 가능)
public class OrderServiceImpl implements OrderService{

    /*DIP, OCP를 지키지 않았던 경우*/
//    // 추상(interface), 구체(구현) 클래스에도 의존 중....
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //    // 추상(interface), 구체(구현) 클래스에도 의존 중.... -> DIP(추상에만 의존하라) 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    // OCP 위반: 변경하지 않고 확장할 수 있어야하나, 코드 변경이 이루어짐
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /* DIP(추상에만 의존하도록 변경) + 누군가 OrderServiceImpl에 DiscountPolicy 구현 객체를 대신 생성 후 주입해줘야한다.*/
    // final 제거: 무조건 값을 할당해야하기 때문에--수정자 주입 및 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 final 사용 불가
    // final: 생성자 주입할 때는 사용(누락을 막을 수 있다.)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    /*//필드 주입-> 쓰지마
    //외부에서 변경 불가능, DI 프레임워크가 없으면 아무것도 할 수 없다.
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;*/


    //생성자 주입(불변, 필수: 생성자 호출시점에 딱 1번 호출, 의존관계에 사용)
    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
//    @Autowired//생성자가 1개이면 Autowired 생략 가능, 매칭정리: 타입, 2개 이상일때 필드명, 메소드명
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy") */@MainDiscountPolicy DiscountPolicy discountPolicy) {
        //@Qualifier는 @Qualifier끼리 매칭, bean 이름 매칭, NoSuchBeanDefinitionException 예외 발생
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    /*//수정자 주입(setter 주입)-옵션이 필요하면 생성자 주입과 같이 사용
    //선택, 변경 가능성이 있는 의존관계에 사용
    //자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
    @Autowired(required = false)//주입할 대상이 없어도 동작하게 하려면면
   public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    //일반 메서드 주입
    //한번에 여러 필드를 주입 받을 수 있다
    //일반적으로 잘 사용x -> setter 주입, 생성자 주입으로 대체 중
    //의존관계 자동주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작(@Component)가 달려야
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
