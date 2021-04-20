package com.hello.core.singleton;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigrationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);


        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        //순수한 class라면 xxxCGLIB가 붙지않음
        System.out.println("bean = " + bean.getClass());//class com.hello.core.AppConfig$$EnhancerBySpringCGLIB$$bdbad3e9

        //이유: @Configuration을 달면 CGLIB라는 바이트코드 조작 라이브러리를 통해
        //     AppConfig 클래스를 상속받은 AppConfig@CGLIB 라는 임의의 다른 클래스를 만들고 스프링 빈으로 등록
        //     @Bean이 붙은 메서드마다 스프링 빈이 존재하면 존재하는 빈을 반환하고 스프링 빈이 없으면 기존 로직을 실행(singleton이 되도록 보장)


    }
}
