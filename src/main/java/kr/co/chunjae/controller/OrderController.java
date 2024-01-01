package kr.co.chunjae.controller;

import kr.co.chunjae.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC를 담당하는 프레젠테이션 계층
 * */

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/ISBN1234/2")
    public String process() {

        orderService.confirmOrder("ISBN1234", 2);
        return "redirect:/books";
    }
}
