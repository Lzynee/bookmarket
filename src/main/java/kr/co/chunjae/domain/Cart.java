package kr.co.chunjae.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Cart {

    private String cartId;  // 장바구니 ID
    private Map<String, CartItem> cartItems;  // 장바구니 항목
    private int grandTotal;  // 총액

    // 기본 생성자
    public Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }

    // 일반 생성자
    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    // 장바구니에 등록되는 도서 가격의 총액 산출
    public void updateGrandTotal() {

        grandTotal = 0;

        for (CartItem item : cartItems.values()) {
            grandTotal = grandTotal + new BigDecimal("item.getTotalPrice()").intValue();
        }
    }

    // cartId 필드에 대한 hashCode()와 equals() 메서드
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        Cart other = (Cart) obj;

        if (cartId == null) {

            if (other.cartId != null)
                return false;

        } else if (!cartId.equals(other.cartId)) {
            return false;
        }

        return true;
    }
}
