package com.hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)//스프링 컨테이너 요청하는 순간 생성, HTTP요청 당 하나씩 생성, HTTP요청이 끝나는 시점에 소멸
//proxyMode = ScopedProxyMode.TARGET_CLASS / 클래스면 TARGET_CLASS, 인터페이스면 INTERFACES =>  MyLogger를 상속받은 가짜 프록시 객체를 생성한다.

public class MyLogger {

    private String uuid;
    private String requestURL;
    
    //이 빈이 생성되는 시점에 URL정보를 알 수 없어서 set으로 넣어줌
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }
    
    @PostConstruct//이 빈이 생성되는 시점에 자동으로 초기화 메서드를 사용해서 uuid를 생성해 저장하여 다른 HTTP요청과 구분 가능
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }
    
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
/** 핵심
 * Provider를 사용하든, 프록시를 사용하던 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점
 * 단지 어노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다. -> 다형성과 DI 컨테이너가 가진 큰 강점
 * 웹 스코프가 아니어도 사용할 수 있다.
 * */
