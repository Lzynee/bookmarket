package kr.co.chunjae.controller;

import kr.co.chunjae.domain.Cart;
import kr.co.chunjae.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * MVC를 담당하는 프레젠테이션 계층
 * */

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 웹 요청 URL이 ... /cart/일 때 요청 처리 메서드
    @GetMapping
    public String requestCartId(HttpServletRequest request) {

        String sessionid = request.getSession(true).getId();  // 세션 ID 값을 가져온다.
        return "redirect:/cart/" + sessionid;  // 가져온 세션 ID 값을 URI cart/sessionId로 리다이렉션한다.
    }

    // 웹 요청 URI가 ... /cart/고 HTTP 메서드가 POST일 때 매핑된다.
    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart) {  // Cart 클래스 정보를 HTTP 요청 body로 전달받는다.
        return cartService.create(cart);  // 새로 생성한 장바구니를 HTTP 응답 body로 전달한다.
    }

    // 웹 요청 URI가 ... /cart/cartId이고 HTTP 메서드가 GET 방식일 때 매핑된다.
    @GetMapping
    public String requestCartList(@PathVariable(value = "cartId") String cartId,
                                  Model model) {  // 경로 변수 : cardId

        // cartId에 대해 장바구니에 등록된 모든 정보를 읽어 와 커맨드 객체 cart 속성에 등록한다.
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    // 요청 URL에서 경로 변수인 cartId에 대해 장바구니에 등록된 모든 정보를 가져온다.
    @PutMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartService.read(cartId);
    }
}
