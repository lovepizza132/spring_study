package com.hello.core;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.*;
import com.hello.core.order.OrderServiceImpl;
import com.hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** SRP 단일 책임 원칙:
 * 한 클래스는 하나의 책임만 가져야 한다.
 * 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당
 * 클라이언트 객체는 실행하는 책임만 담당*/

/** OCP: 확장에는 열려있으나 변경에는 닫혀있어야한다.
 * 다형성을 사용하고 클라이언트가 DIP를 지킴
 * 애플리케이션을 사용 영역과 구성 영역으로 나눔
 * AppConfig가 구체화를 변경해서 (FixDiscountPolicy(); -> RateDiscountPolicy();) 의존관계를 주입한다*/

/** DIP(Dependency Inversion Principle) 의존관계 역전원칙:
 * 프로그래머는 "추상화(interface)에 의존해야지 구체화에 의존하면 안된다
 * 의존성 주입(생성자 주입 사용)을 통해 원칙 수행
 * AppConfig에서 FixDiscountPolicy 객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존관계를 주입(DI)*/



/** IoC(Inversion of Control): 제어의 역전
 * 프로그램에 대한 제어 흐름에 대한 권한이 모두 AppConfig가 지님(외부에서 제어)
 * 프로그램의 제어 흐름을 직접 제어하는 것이 아닌, 외부에서 관리하는 것을 IoC라고 한다.*/

/** DI(Dependency Injection) 의존관계 주입
 * 클라이언트 코드(OrderServiceImpl)은 DiscountPolicy 인터페이스에 의존하며, 실제 어떤 구현 객체가 사용될지는 모름
 * 정적인 클래스 의존관계와, 실행 시점에 결정되는 동적인 객체(인스터스) 의존관계
 * 정적인 클래스 의존관계 1: 클래스가 사용하는 import 코드만 보고 의존관계를 파악할 수 있다.(OrderServiceImpl -> MemberRepository, DiscountPolicy 에 의존)
 * 정적인 클래스 의존관계 2: 이러한 클래스 의존관계에서는 실제 어떤 객체가 주입 될지 알 수 없다.(FixDiscountPolicy 인지 RateDiscountPolicy가 주입되었는지 알 수 없다.)
 * 동적인 객체 인스턴스 의존 관계: 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입이라 한다.
 * 즉, 의존관계 주입을 통해 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.*/

/*프레임워크 vs 라이브러리
* 프레임워크: 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크(ex: JUit)
* 라이브러리: 내가 작성한 코드가 직접 제어의 흐름을 담당*/

/** 스프링 컨테이너 생성과정
 * 1. 스프링 컨테이너 생성: ApplicationContext 인터페이스를 AnnotationConfigApplicationContext 구현체로 객체생성 (다형성 사용), AppConfig.class를 구성정보로 제공
 * -> ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
 * 2. 스프링 빈 등록: @Bean(name="memberService) or @Bean의 메서드 이름
 * -> @Bean의 이름은 항상 다르게 부여
 * 3. 스프링 빈 의존관계 설정 - 준비
 * 4. 스프링 빈 의존관계 설정 - 완료 (단순 호출하는 것처럼 보이나 다름)
 * -> 스프링은 빈을 생성하고 의존관계를 주입하는 단계가 나누어져 있다.*/


/** 싱글톤 컨테이너
 * 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
 * ->싱글톤 레지스트리
 *
 * 스프링 컨테이너의 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지
 * -> 싱글톤 패턴을 위한 지저분한 코드 없어짐, DIP, OCP, 테스트, private 생성자로부터 자유롭게 싱글톤 사용 가능
 *
 * 싱글톤 패턴은 객체 인스턴스를 하나만 생성해서 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 상태를 유지(stateful)하게 설계하면 안된다.
 * ->무상태(stateless)로 설계(스프링 빈의 필드에 공유 값을 설정하면 큰 일남)
 *      a. 특정 클라이언트에 의존적인 필드가 있으면 안된다.
 *      b. 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다(중요-자주 발생).
 *      c. 가급적 읽기만 가능해야 한다.
 *      d. 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야한다.*/

/* 싱글톤 패턴의 문제점
* 1. 싱글톤 패턴을 구현하는 코드가 많이 들어간다
* 2. 의존관계상 클라이언트가 구체 클래스에 의존한다 -> DIP를 위반(구체화된 클래스 객체 생성 후 getInstance() 사용)
* 3. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다
* 4. 테스트하기 어렵다
* 5. 내부 속성을 변경하거나 초기화 하기 어렵다
* 6. private 생성자로 자식 클래스를 만들기 어렵다
* 7. 결론적으로 유연성이 떨어진다.
* 8. 안티패턴으로 불리기도 한다*/

// 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
// OCP: 클라이언트 코드를 수정하지 않고 확장할 수 있다.
@Configuration//스프링에서의 설정정보 = 애플리케이션의 설정정보
public class AppConfig {

    @Bean //스프링 컨테이너에 등록이 됨
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}
