package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")//객체 파라미터-> HTTP 메시지 컨버터가 HTTP 메시지 바디 내용을 우리가 원하는 문자나 객체로 변환해 준다.
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {//@RequestBody를 생략하면 @ModelAttribute로 된다. 단순 타입은 @RequestParam으로 됨 즉, 요청 파라미터를 적용하게 됨
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")//객체 파라미터-> HTTP 메시지 컨버터가 HTTP 메시지 바디 내용을 우리가 원하는 문자나 객체로 변환해 준다.
    public String requestBodyJsonV4(HttpEntity<HelloData> HttpEntity) throws IOException {//@RequestBody를 생략하면 @ModelAttribute로 된다. 단순 타입은 @RequestParam으로 됨 즉, 요청 파라미터를 적용하게 됨
        HelloData data = HttpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        return "ok";
    }

    //@RequestBody 요청
    // -> JSON 요청 -> HTTP 메시지 컨버터 -> 객체
    //@ResponseBody 요청
    // 객체 -> HTTP 메시지 컨버터 -> JSON 응답
    @ResponseBody
    @PostMapping("/request-body-json-v5")//객체 파라미터-> HTTP 메시지 컨버터가 HTTP 메시지 바디 내용을 우리가 원하는 문자나 객체로 변환해 준다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        return data;
    }
}
/**
 * @RequestBody
 * @RequestBody 를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회할 수 있다. 참고로 헤더 정보가
 * 필요하다면 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면 된다.
 * 이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam ,
 *
 * @ModelAttribute 와는 전혀 관계가 없다.
 * 요청 파라미터 vs HTTP 메시지 바디
 * 요청 파라미터를 조회하는 기능: @RequestParam , @ModelAttribute
 * HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
 *
 * @ResponseBody
 * @ResponseBody 를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다.
 * 물론 이 경우에도 view를 사용하지 않는다
 * 물론 이 경우에도 'HttpEntity'를 사용해도 된다.
 */
