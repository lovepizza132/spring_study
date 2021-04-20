package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메소드 실행이 끝나고 동작하는 callback 메소드
    @AfterEach
    public void  afterEach(){
        repository.clearStore(); // test 후 클리어 - test는 순서와 관계없이 의존관계 없이 설계해야함
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // optional에서 값을 꺼낼 때는 get()으로 꺼낼 수 있다 하지만 좋은 방법은 아니다
        /*System.out.println("result = " + (result == member));*/
        /*Assertions.assertEquals(member, result);*/
        assertThat(member).isEqualTo(result); // alt + enter static import로 Assertions.assertThat()말고 단축해서 사용 가능

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
