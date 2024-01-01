package kr.co.chunjae.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Cart implements Serializable {

    private String cartId;  // 장바구니 ID
    private Map<String, CartItem> cartItems;  // 장바구니 항목
    private BigDecimal grandTotal;  // 총액

    // 기본 생성자
    public Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = BigDecimal.valueOf(0);
    }

    // 일반 생성자
    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    // 장바구니에 등록되는 도서 가격의 총액 산출
    public void updateGrandTotal() {

        grandTotal = BigDecimal.ZERO;

        for (CartItem item : cartItems.values()) {
            grandTotal = grandTotal.add(item.getTotalPrice());
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

    /**
     * RESTful 웹 서비스 구현
     * 장바구니 기능
     * */
    // 도서 목록 중 선택한 도서를 장바구니에 등록한다.
    public void addCartItem(CartItem item) {

        String bookId = item.getBook().getBookId();  // 현재 등록하기 위한 도서 ID 가져오기

        // 도서 ID가 cartItems 객체에 등록되어 있는지 여부 확인
        if (cartItems.containsKey(bookId)) {
            CartItem cartItem = cartItems.get(bookId);  // 등록된 도서 ID에 대한 정보 가져오기
            // 등록된 도서 ID의 개수 추가 저장
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
            cartItems.put(bookId, cartItem);  // 등록된 도서 ID에 대한 변경 정보(cartItem) 저장

        } else {
            cartItems.put(bookId, item);  // 도서 ID에 대한 도서 정보(item) 저장
        }

        updateGrandTotal();  // 총액 갱신
    }

    // 장바구니에 등록된 도서 항목을 삭제한다.
    public void removeCartItem(CartItem item) {

        String bookId = item.getBook().getBookId();  // 삭제할 도서 ID 가져오기
        cartItems.remove(bookId);  // bookId 도서 삭제

        updateGrandTotal();  // 총액 갱신
    }
}
