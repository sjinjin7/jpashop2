package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")//mapping을 무엇으로 할 것인가 // 외래키 이름이 member_id라고 보면 됨
    private Member member;

    // JPQL select o From order o; -> SQL select * from order 100 + 1

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   // 일대일 관계일 경우 액세스가 많은 곳에 연관관계주인을 줌
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간
    //기존의 경우 Date 를 타입으로 썻을때 jpa가 인식 할 수 잇도록 별도의 날짜관련 어노테이션을 작성해주어야 했다.
    // 자바 8에서는 LocaDateTime을 사용 할 경우 hibernate가 알아서 매핑을 해 준다.

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
