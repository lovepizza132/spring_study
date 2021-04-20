package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//안에 @ComponentScan에의해 자동으로 스프링 빈으로 등록(@Component역할) + RequestMappingHandlerMapping이 매핑 정보로 인식(@RequestMapping역할)
//@Component// 스프링 빈에 등록
//@RequestMapping// 클래스 레벨에 있으면 인식 됨
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
