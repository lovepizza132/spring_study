package com.hello.core.order;

import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder(){
        //생성자 주입을 사용해야 어떤 객체를 넣어야하는 의존관계를 컴파일 오류로 보여줘 디텍트하기 쉽다
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        Order order = orderService.createOrder(1L, "itemaA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
