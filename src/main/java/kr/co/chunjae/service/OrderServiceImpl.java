package kr.co.chunjae.service;

import kr.co.chunjae.domain.Book;
import kr.co.chunjae.domain.Order;
import kr.co.chunjae.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Override
    public void confirmOrder(String bookId, long quantity) {

        Book bookById = bookRepository.getBookById(bookId);

        if (bookById.getUnitsInStock() < quantity) {
            throw new IllegalArgumentException("품절입니다. 사용 가능한 재고수 : "
                    + bookById.getUnitsInStock());
        }

        bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
    }

    @Override
    public Long saveOrder(Order order) {

        Long orderId = orderRepository.saveOrder(order);
        cartService.delete(order.getCart().getCartId());
        return orderId;
    }
}
