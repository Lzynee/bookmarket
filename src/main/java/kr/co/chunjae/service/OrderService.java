package kr.co.chunjae.service;

import kr.co.chunjae.domain.Order;

/**
 * 주문 처리 정보를 반환하는 서비스 계층
 * */

public interface OrderService {

    void confirmOrder(String bookId, long quantity);
    Long saveOrder(Order order);
}
