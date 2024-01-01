package kr.co.chunjae.service;

/**
 * CartService 인터페이스에 정의한 create()와 read() 메서드를 구현한다.
 * */

import kr.co.chunjae.domain.Cart;
import kr.co.chunjae.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    // 장바구니 저장소 객체에서 생성한 장바구니를 가져와 반환한다.
    @Override
    public Cart create(Cart cart) {
        return cartRepository.create(cart);
    }

    // 저장소 객체에서 장바구니 ID에 대해 장바구니에 등록된 모든 정보를 가져와 반환한다.
    @Override
    public Cart read(String cartId) {
        return cartRepository.read(cartId);
    }

    @Override
    public void update(String cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }
}
