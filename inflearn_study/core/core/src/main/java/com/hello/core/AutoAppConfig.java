package com.hello.core;

import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록함
@ComponentScan(
//        basePackages = 특정 위치의 하위 디렉토리만 스캔 / default: @ComponentScan이 있는 클래스의 패키지가 시작 위치(시작위치에서 하위 디렉토리로)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//@Configuration 이 달린 class는 뺀다(ex AppConfig)
)
public class AutoAppConfig {

//    //빈의 이름이 겹치면 수동 빈이 자동빈을 오버라이딩한다(수동빈이 우선권을 지님)
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}

/** ComponentScan 정리
 * 1. AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
 *    AutoAppConfig 클래스의 설정 정보로 스프링 빈을 등록하겠다.
 * 2. AutoAppConfig 클래스 안의 @Configuration 스프링의 설정 정보를 구성하는 어노테이션을 단다(안에 @Component 가 있어 스캔 됨)
 * 3. 스프링 컨테이너내에 스프링 빈(싱글톤 패턴 상태)을 등록하고자 @ComponentScan을 통해 스프링의 설정 정보(객체 생성 및 의존관계 주입)를 구현체 클래스마다 달아놓은 @Component 으로 찾음
 *    -> 1. 모든 스프링 빈을 먼저 등록(@Component 수 만큼): 등록되는 스프링 빈 이름의 default 값은 @Component가 붙은 메소드명(첫글자 소문자)
 *    -> 2. 등록된 스프링 빈 간의 의존관계 설정: 해당 어노테이션에서 @Autowired를 통해 AppConfig 클래스에서 수동으로 설정한 의존관계 주입을 자동으로 진행(getBean(객체타입.class);처럼 타입으로 찾아 주입)
 *
 * 추가: @Configuration을 사용한 스프링 컨테이너 내의 스프링 빈은 왜 싱글톤 패턴인가...
 *      @Configuration을 달면 CGLIB라는 바이트코드 조작 라이브러리를 통해
 *         AppConfig 클래스(스프링 설정정보)를 상속받은 AppConfig@CGLIB 라는 임의의 다른 클래스를 만들고 이를 스프링 빈으로 등록
 *         @Bean이 붙은 메서드마다 스프링 빈이 존재하면 존재하는 빈을 반환하고 스프링 빈이 없으면 기존 로직을 실행하여 객체를 1개만 생성(singleton이 되도록 보장)
 * */
