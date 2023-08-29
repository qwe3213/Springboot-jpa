package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// 어딘가에 저장될수있도록 Embeddable 어노테이션 사용
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    // 값 타입은 병경 불가능하게 설계해야 함.
    // Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만든다.
    public Address(String city , String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
