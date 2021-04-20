package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional//jpa사용시 data 변경은 모두 transaction안에서 이루어줘야한다
public class MemberSerivce {

    private final MemberRepository memberRepository;

    public MemberSerivce(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*
    * 회원 가입
    */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();



    }

    private void validateDuplicateMember(Member member) {
        /*Optional<Member> result = memberRepository.findByName(member.getName());*/
        // 단축키 ctrl + alt + v 리턴형 알아서 정해줌

        // Optional에서 값 꺼내기 get();, orElseGet();

        // ifPresens 만약 null이 아니고 member에 값이 있으면 optional을 사용했기 때문에 사용 가능
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    
    /*
    * 특정 회원 조회
    */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
