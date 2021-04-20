package com.hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    //구현 객체를 MemoryRepository를 선택
//    //다형성에 의해서 MemoryMemberRepository의 오버라이딩 된 메서드를 호출할 수 있다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 추상만 의존하도록 설계(더 이상 구현(구체적인) 클래스인 MemoryMemberRepository에 의존하지 않는다.)
    private MemberRepository memberRepository;

    // 생성자 주입: 생성자를 통해서 memberRepository의 구현체가 뭐가 들어갈지 구성
    // 생성한 객체 인스턴스의 참조(레퍼런스==MemberRepository)를 생성자를 통해서 주입(연결)해준다.-> Dependecy Injection(의존관계 주입)
    @Autowired //자동 의존관계 주입(ComponentScan을 사용하면 수동으로 의존관계를 주입해주지 못하기 때문에)-> ac.getBean(MemberRepository.class) 와 비슷
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
