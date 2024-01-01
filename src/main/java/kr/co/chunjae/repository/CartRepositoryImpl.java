package kr.co.chunjae.repository;

/**
 * CartRepository 인터페이스에 정의한 create()와 read() 메서드를 구현한다.
 * */

import kr.co.chunjae.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private Map<String, Cart> listOfCarts;

    public CartRepositoryImpl() {
        listOfCarts = new HashMap<String, Cart>();
    }

    // 새로운 장바구니를 생성하여 장바구니 ID를 등록하고
    // 생성된 장바구니 객체를 반환하는 메서드
    @Override
    public Cart create(Cart cart) {

        if (listOfCarts.keySet().contains(cart.getCartId())) {
            // 동일한 장바구니 ID가 존재할 경우 예외 처리
            throw new IllegalArgumentException(String.format("장바구니를 생성할 수 없습니다." +
                    "장바구니 id(%)가 존재합니다.", cart.getCartId()));
        }

        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }

    // 장바구니 ID를 이용하여
    // 장바구니에 등록된 모든 정보를 가져와 반환하는 메서드
    @Override
    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }
}
