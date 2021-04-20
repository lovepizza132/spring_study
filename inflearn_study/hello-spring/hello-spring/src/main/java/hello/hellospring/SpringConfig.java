package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 spring bean을 등록하는 방법
@Configuration
public class SpringConfig {

    // bean에 추가안하는 이유: SpringDataJpaMemberRepository(interface)가 jpa가 제공하는 JpaRepository(interface)를 extends하고 있으면, 자동으로 spring bean에 구현체를 만들어서 등록 따라서 우리는 가져다가 쓰기만 하면 됨
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberSerivce memberSerivce(){
        return new MemberSerivce(memberRepository);
    }

//    TimeTraceAop Bean 직접 등록
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    JPA 사용시
////    @PersistenceContext 원래는 이거 써야함
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//-----------------------------------------------------
//    jdbc 사용시
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//
//    @Bean
//    public MemberSerivce memberSerivce(){
//        return new MemberSerivce(memberRepository());
//    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new JdbcMemberRepository(dataSource);
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
////        return  new JpaMemberRepository(em);
//
//    }
}
