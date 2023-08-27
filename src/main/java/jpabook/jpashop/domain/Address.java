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

}
