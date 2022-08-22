package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded // @Embedded나 @Embeddable 둘 중 하나만 있어도 되지만 관례적으로 둘다 작성 함
    private Address address;

    @OneToMany(mappedBy = "member")//Order클래스(테이블)에 있는 member 필드에 의해 매핑이 된다는 의미
    private List<Order> orders = new ArrayList<>();

}
