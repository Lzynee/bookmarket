package kr.co.chunjae.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@SuppressWarnings("serial")
public class Order implements Serializable {

    private Long orderId;  // 주문 ID
    private Cart cart;  // 장바구니 객체
    private Customer customer;  // 고객 객체
    private Shipping shipping;  // 배송지 객체

    public Order() {
        this.customer = new Customer();
        this.shipping = new Shipping();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Order other = (Order) obj;

        if (orderId == null) {

            if (other.orderId != null)
                return false;

        } else if (!orderId.equals(other.orderId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());

        return result;
    }
}
