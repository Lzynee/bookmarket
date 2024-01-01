package kr.co.chunjae.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuppressWarnings("serial")
public class Shipping implements Serializable {

    private String name;  // 배송 고객 이름

    @DateTimeFormat(pattern = "yyyy/MM/dd")  // date 속성의 제약 사항으로 날짜 표현 형식을 지정
    private Date date;  // 배송일
    private Address address;  // 배송 주소 객체

    public Shipping() {
        this.address = new Address();
    }
}
