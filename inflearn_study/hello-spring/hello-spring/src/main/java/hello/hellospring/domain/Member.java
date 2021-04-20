package hello.hellospring.domain;

import javax.persistence.*;

// JPA는 JAVA의 표준 interface이며, 다른 업체의 구현체를 사용한다.
@Entity
public class Member {

    // PK 매핑(id 값이 자동 생성되기 때문에 @GeneratedValue DB가 알아서 생성해 주는건 IDENTITY)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
