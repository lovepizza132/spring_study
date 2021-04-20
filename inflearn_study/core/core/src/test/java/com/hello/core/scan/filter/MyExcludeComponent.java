package com.hello.core.scan.filter;

import java.lang.annotation.*;

//ComponentScan에 추가한다는 의미
@Target(ElementType.TYPE)//클래스레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
