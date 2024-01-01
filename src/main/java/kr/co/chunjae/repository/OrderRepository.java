package kr.co.chunjae.repository;

import kr.co.chunjae.domain.Order;

/**
 * 도서 주문 처리 정보를 관리하는 퍼시스턴스 계층
 * */
// 저장소 객체 구현
public interface OrderRepository {
    Long saveOrder(Order order);
}
