package com.hello.core.web;

import com.hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;//dependecy를 찾을 수 있는
private final MyLogger myLogger;//dependecy를 찾을 수 있는

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
//        MyLogger myLogger = myLoggerProvider.getObject();//여기서 myLogger request 빈 생성 + init이 호출되면서 uuid 형성(각 bean마다 고유의 uuid값 제공)
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger= " + myLogger.getClass());//myLogger= class com.hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$4747118c
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
