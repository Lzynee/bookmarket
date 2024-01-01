package kr.co.chunjae.controller;

import kr.co.chunjae.domain.*;
import kr.co.chunjae.exception.BookIdException;
import kr.co.chunjae.service.BookService;
import kr.co.chunjae.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * MVC를 담당하는 프레젠테이션 계층
 */

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

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
    @GetMapping("/{cartId}")
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

    // 장바구니 등록 메서드
    // 경로 변수 bookId에 대해 해당 도서를 장바구니에 추가로 등록하고 장바구니를 갱신한다.
    @PutMapping("/add/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)  // 오류 응답 상태 코드 설정
    public void addCartByNewItem(@PathVariable String bookId,
                                 HttpServletRequest request) {

        // 장바구니 ID인 세션ID 가져오기
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);  // 장바구니에 등록된 모든 정보 얻어 오기

        if (cart == null)
            cart = cartService.create(new Cart(sessionId));

        // 경로 변수 bookId에 대한 정보 얻어 오기
        Book book = bookService.getBookById(bookId);

        if (book == null)
            throw new IllegalArgumentException(new BookIdException(bookId));

        // bookId에 대한 도서 정보를 장바구니에 등록하기
        cart.addCartItem(new CartItem(book));
        cartService.update(sessionId, cart);  // 세션 ID에 대한 장바구니 갱신하기
    }

    // 장바구니 항목 삭제 메서드
    @PutMapping("/remove/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartByItem(@PathVariable String bookId,
                                 HttpServletRequest request) {

        // 장바구니 ID인 세션ID 가져오기
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);  // 장바구니에 등록된 모든 정보 얻어 오기

        if (cart == null)
            cart = cartService.create(new Cart(sessionId));

        // 경로 변수 bookId에 대한 정보 얻어 오기
        Book book = bookService.getBookById(bookId);

        if (book == null)
            throw new IllegalArgumentException(new BookIdException(bookId));

        // bookId에 대한 도서 정보를 장바구니에서 삭제하기
        cart.removeCartItem(new CartItem(book));
        cartService.update(sessionId, cart);  // 세션 ID에 대한 장바구니 갱신하기
    }
}
