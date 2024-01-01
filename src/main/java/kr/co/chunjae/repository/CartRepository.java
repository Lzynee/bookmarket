package kr.co.chunjae.repository;

/**
 * 장바구니 정보를 관리하는 퍼시스턴스 계층
 * */

import kr.co.chunjae.domain.Cart;

public interface CartRepository {

    Cart create(Cart cart);
    Cart read(String cartId);

    void update(String cartId, Cart cart);  // 장바구니 등록 메서드
    void delete(String cartId);  // 장바구니 삭제 메서드
}
