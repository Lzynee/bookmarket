package kr.co.chunjae.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 장바구니 정보를 담는 도메인 객체
 * */

public class CartItem {

    private Book book;  // 도서
    private int quantity;  // 도서 개수
    private int totalPrice;  // 도서 가격

    // 기본 생성자
    public CartItem() {
        // TODO Auto-generated constructor stub
    }

    // 일반 생성자
    public CartItem(Book book) {
        super();
        this.book = book;
        this.quantity = 1;
        this.totalPrice = book.getUnitPrice();
    }

    // Getter & Setter
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    // 장바구니에 등록되는 도서별 총액 산출
    public void updateTotalPrice() {
        totalPrice = this.book.getUnitPrice() * this.quantity;
    }

    @Override
    public int hashCode() {

        final int prime = 31;

        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        CartItem other = (CartItem) obj;

        if (book == null) {

            if (other.book != null)
                return false;

        } else if (!book.equals(other.book)) {
            return false;
        }

        return true;
    }
}
