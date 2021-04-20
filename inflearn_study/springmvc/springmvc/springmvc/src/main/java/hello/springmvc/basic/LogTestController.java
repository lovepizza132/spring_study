package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
//@RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
//따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.
@RestController
@Slf4j//private final Logger log = LoggerFactory.getLogger(getClass());자동으로 들어감
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);
        log.trace(" trace my log=" + name);//이렇게 사용하면 안됨 -> 이미 연산이 일어남(쓸모 없는 resource사용)

        log.trace(" trace log={}", name);
        log.debug(" debut log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);


        return "ok";

    }
}
