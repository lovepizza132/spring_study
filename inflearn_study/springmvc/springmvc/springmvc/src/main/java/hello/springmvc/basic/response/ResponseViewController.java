package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!!!");
        return "response/hello";//@Controller이면서 String으로 반환하면 view의 논리적 이름이다.
    }

    @ResponseBody//뷰 리졸버를 실행하지 않고 HTTP 메시지 컨버터 작동
    @RequestMapping("/response-view-222")
    public String responseView222(Model model){
        model.addAttribute("data", "hello!!!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!!!");
        //컨트롤러의 경로이름과 뷰의 논리적 이름이 똑같으면 논리적 뷰의 이름으로 진행됨
    }
}
