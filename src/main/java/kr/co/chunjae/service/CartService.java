package kr.co.chunjae.service;


import kr.co.chunjae.domain.Cart;

/**
 * 도서 장바구니 정보를 반환하는 서비스 계층
 * */

public interface CartService {

    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);  // 장바구니 등록 메서드
    void delete(String cartId);  // 장바구니 삭제 메서드
}
