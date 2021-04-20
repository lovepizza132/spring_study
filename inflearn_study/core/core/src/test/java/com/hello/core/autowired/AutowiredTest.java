package com.hello.core.autowired;

import com.hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //자동 주입할 의존관계가 없으면 메서드 자체가 호출이 안됨(Member가 스프링 빈이 아님)
        @Autowired(required = false)// 없으면 Member가 스프링 빈으로 등록이 안되있기에 오류 뜸
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        //자동 주입할 대상이 없으면 null 출력
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        //스프링 빈이 없으면 Optional.empty 출력
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
