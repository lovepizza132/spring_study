package com.hello.core.discount;

import com.hello.core.annotation.MainDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")//문자이기에 오타가 나도 컴파일 오류를 잡을 수 가 없다
@MainDiscountPolicy//직접 annotation을 만들면, @Qualifier의 컴파일 오류가 나지않은 오타 같은 부분들을 보완할 수 있다.
//@Primary//우선순위 최상위==의존관계 주입 됨(하지만 @Qualifier>@Primary 보다 우선순위가 높다)
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
