package com.hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //ApplicationContext 밑에 ConfigurableApplicationContext 밑에 AnnotationConfigApplicationContext 이 있다.


        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();// 사용하려면 AnnotataionCofigApplicationContext or ConfigurableApplicationContext
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean/*(initMethod = "init", destroyMethod = "close")*///코드가 아니라 설정정보를 사용하기 때문에 코드를 고칠 수 없는 외부라이브러리에도 적용 가능
        public NetworkClient networkClient() {//destroyMethod의 default=="(inferred)"이며 추론은 close나 shutdown 메서드를 찾아 실행
            //생성자
            NetworkClient networkClient = new NetworkClient();
            //초기화
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
/** 객체의 생성(메모리에 할당하는 것까지)과 초기화는 분리하자(실제 동작하는 행위는 별도의 초기화 메서드로 분리하는게 좋다)
 * 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
 * 초기화는 이렇게 생성된 값들을 활용해서 외부 커텍션을 연결하는 등 무거운 동작을 수행한다.
 * 즉, 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화하는 부분을 나눈게 유지보수에 좋다.*/