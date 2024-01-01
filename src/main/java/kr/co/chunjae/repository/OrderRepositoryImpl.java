package kr.co.chunjae.repository;

/**
 * 도서 주문 처리 정보를 관리하는 퍼시스턴스 계층
 * */

import kr.co.chunjae.domain.Order;
import lombok.Synchronized;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// OrderRepository 인터페이스에 정의한 saveOrder() 메서드를 구현한다.
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private Map<Long, Order> listOfOrders;
    private long nextOrderId;

    public OrderRepositoryImpl() {
        listOfOrders = new HashMap<Long, Order>();
        nextOrderId = 2000;
    }

    @Override
    public Long saveOrder(Order order) {
        // 주문 내역에 대한 ID와 주문 내역을 저장하고 주문 내역 ID를 반환한다.
        order.setOrderId(getNextOrderId());
        listOfOrders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    private Synchronized

    long getNextOrderId() {
        return nextOrderId++;
    }
}
