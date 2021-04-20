package com.hello.core.web;

import com.hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvider;//의존관계 주입
    private final MyLogger myLogger;//의존관계 주입

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id= " + id);
    }
}
