package com.hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");

        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.close");
        }
    }
}


/** 프로토타입 빈 요청
 * 1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청한다.
 * 2. 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성 후 의존관계를 주입한다.
 * 3. 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환한다.
 * 4. 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환한다.
 * ---핵심---
 * 스프링 컨테이너는 프로토타입 빈을 생성 후, 의존관계 주입, 초기화까지만 처리
 * 이후 스프링 컨테이너는 프로토타입 빈을 관리하지 않는다.
 * 따라서, 프로토타입 빈을 관리할 책인은 프로토타입 빈을 받은 클라이언트에 있다.(수동으로 요청 prototypeBean1.destroy();)
 * 그래서 @PreDestroy 같은 종료 메서드가 호출되지 않는다.
 * */
