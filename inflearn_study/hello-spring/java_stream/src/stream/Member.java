package stream;

//import lombok.Getter;
//import lombok.RequiredArgsConstructor;

//@Getter
//@RequiredArgsConstructor
public class Member {
    private final Long memberNo;
    private final String name;

    public Member(long memberNo, String name){
        this.memberNo = memberNo;
        this.name = name;
    }

    public Long getMemberNo() {
        return memberNo;
    }

    public String getName() {
        return name;
    }
}