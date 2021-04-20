package com.hello.core;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        //스프링 사용전
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        // AppConfig 사용전
//        //        MemberService memberService = new MemberServiceImpl();

        //스프링 사용
        // ApplicationContext == 스프링 컨테이너, 인터페이스이다(즉, AnnotationConfigApplicationContext을 통해 다형성 구현(추상->구체-구현체 이용))
        // 스프링 컨테이너는 xml기반으로도 만들 수 있다.(여기서는 annotation 기반의 자바 설정 클래스로 만듬)
        // AppConfig에 있는 환경설정(@Bean 포함)을 스프링 컨테이너에 객체생성된 것들을 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //@Bean에 붙은 메서드 이름, 타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
