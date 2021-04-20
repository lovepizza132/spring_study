package com.hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

public class NetworkClient /*implements InitializingBean, DisposableBean */{
    // 초기화, 소멸 인터페이스의 단점(초창기 방법이라 거의 사용 안함)
    //스프링 전용 인터페이스-해당 코드가 스프링 전용 인터페이스에 의존한다.
    //초기화, 소멸 메서드의 이름을 변경할 수 없다.
    //내가 코드를 칠 수 없는 외부 라이브러리에 적용할 수 없다.

    private String url;

    //생성자
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    /*implements InitializingBean, DisposableBean 사용시 스프링 빈 콜백
    //properties 세팅이 끝나면 호출해 주겠다(의존관계 주입이 끝나면 호출해 주겠다.)
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    //Bean 종료 전 호출
    @Override
    public void destroy() throws Exception {
        disconnect();
    }*/
    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close() {
        disconnect();
    }
    // @PostConstruct, @PreDestory 애노테이션 특징
    //최신 스프링에서 권장하는 방법
    //'javax.annotation.PostConstruct' 스프링에 종속적인 기술이 아닌, JSR-250이라는 자바 표준-> 다른 컨테이너에서도 동작
    //@ComponentScan과 잘 어울림
    //유일한 단점은 외부 라이브러리에 적용을 못함


}
