package kr.co.chunjae.exception;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class CartException extends RuntimeException {

    private String cartId;

    public CartException(String cartId) {
        this.cartId = cartId;
    }
}
