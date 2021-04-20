package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest//스프링 컨테이너와 테스트를 함께 실행
@Transactional//test 케이스에 어노테이션이 있으면 test시작 전 트랜젝션 시작하고, test가 끝나면 rollback(DB에 data가 남지않아 다음 test에 영향X)
class MemberSerivceIntegrationTest {

    @Autowired MemberSerivce memberSerivce;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberSerivce.join(member);

        //then
        Member findMember = memberSerivce.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberSerivce.join(member1);
        //
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberSerivce.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberSerivce.join(member2);
            fail("예외가 발생해야 합니다");
        } catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then

    }

}