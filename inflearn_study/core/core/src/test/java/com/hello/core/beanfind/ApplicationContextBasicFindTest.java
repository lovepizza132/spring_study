package com.hello.core.beanfind;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberSerivce = ac.getBean("memberService", MemberService.class);
        assertThat(memberSerivce).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 빈 조회")
    void findBeanByType(){
        MemberService memberSerivce = ac.getBean(MemberService.class);
        assertThat(memberSerivce).isInstanceOf(MemberServiceImpl.class);
    }

    //유연성이 떨어진다.(프로그래머는 추상에 의존해야한다 구체가 아닌)
    @Test
    @DisplayName("구체 타입으로 빈 조회")
    void findBeanByName2(){
        MemberService memberSerivce = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberSerivce).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //ac.getBean("xxxxx",MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }
}
