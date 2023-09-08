package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name= "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 주문 회원
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    //cascade = CascadeType.ALL 를 사용하여 한번에 order를 persist함
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // 배송정보

    private LocalDateTime orderDate; // 주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    // ==연관관계 메서드 ==//
//    public static void main(String[] args){
//        Member member = members;
//        Order order = orders;
//
//        member.getOrders().add(order);
//        order.setMember(member);
//    }
    // 이코드를 줄이기위해 아래의 방식처럼 작성
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

    //== 생성 메서드 ==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
                                                                     // ... 리스트형식이므로 여러개를 넘기기 위한 문법
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        // 처음상태를 ORDER로 정해둠
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //== 비즈니스 로직 ==//
    /*
     * 주문 취소
     */

    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            // COMP는 배송완료라는 뜻
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
         this.setStatus(OrderStatus.CANCEL);
        // if문 아닐시 취소해줌
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
            // 개수만큼 취소시켜줌
        }
    }

    // == 조회 로직 == //
   /*
    * 전체 주문 가격 조회
    */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
