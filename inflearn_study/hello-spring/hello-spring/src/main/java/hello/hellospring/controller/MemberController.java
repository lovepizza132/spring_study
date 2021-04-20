package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //필드 주입 방법
    //@Autowired private MemberSerivce memberSerivce;

    private final MemberSerivce memberSerivce;

    //spring bean에 등록되어있는 memberService 객체를 넣어 줌 DI
    //생성자 주입 방법
    @Autowired
    public MemberController(MemberSerivce memberSerivce){
        this.memberSerivce = memberSerivce;
    }
    
    //setter 주입 방법: public이라 외부에 노출되어 문제발생할 수 있음-> 추후에 메소드를 호출해 수정되는 위험이 발생할 수 있음
    /*@Autowired
    public void setMemberSerivce(MemberSerivce memberSerivce){
        this.memberSerivce = memberSerivce;
    }*/

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ // 자바빈 프로퍼티 명명법으로 MemberForm에 form에서 보내는 name="name"이 변수"name"과 메소드"setName"이 같아야 set 됨
        Member member = new Member();
        member.setName(form.getName());
        

        memberSerivce.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberSerivce.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }


}
