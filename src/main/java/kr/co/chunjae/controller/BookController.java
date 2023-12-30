package kr.co.chunjae.controller;

import kr.co.chunjae.domain.Book;
import kr.co.chunjae.exception.BookIdException;
import kr.co.chunjae.exception.CategoryException;
import kr.co.chunjae.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 도서 목록 출력
     */

    // 전체
    @GetMapping
    public String requestBookList(Model model) {
        List<Book> list = bookService.getAllBookList();
        model.addAttribute("bookList", list);
        return "books";
    }

    // 전체 (/all)
    @GetMapping("/all")
    public ModelAndView requestAllBooks() {
        ModelAndView mav = new ModelAndView();
        List<Book> list = bookService.getAllBookList();
        mav.addObject("bookList", list);
        mav.setViewName("books");
        return mav;
    }

    // 분야별 검색 결과 (단독 파라미터)
    @GetMapping("/{category}")
    public String requestBooksByCategory(@PathVariable("category")
                                         String bookCategory, Model model) {

        List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);

        // 예외 처리
        // 도서 목록 중 존재하지 않는 도서 분야를 요청하는 경우
        if (booksByCategory == null || booksByCategory.isEmpty()) {
            throw new CategoryException();
        }

        model.addAttribute("bookList", booksByCategory);
        return "books";
    }

    // 분야별 검색 결과 (다중 파라미터)
    public String requestBooksByFilter(@MatrixVariable(pathVar = "bookFilter")
                                       Map<String, List<String>> bookFilter,
                                       Model model) {

        Set<Book> bookListByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", bookFilter);
        return "books";
    }

    /**
     * 상세 조회
     * ID와 일치하는 도서 정보를 출력한다.
     */
    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId,
                                  Model model) {

        Book bookById = bookService.getBookById(bookId);
        model.addAttribute("book", bookById);
        return "book";
    }

    /**
     * 도서 등록
     */
    @GetMapping("/add")
    public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
        return "addBook";
    }

    @PostMapping("/add")
    // 사용자의 입력 값을 커맨드 객체 NewBook으로 매핑할 때 유효성 검사 진행 => 결과 값을 result 객체에 저장
    public String submitAddNewBook(@Valid @ModelAttribute("NewBook") Book book,
                                   BindingResult result) {

        // 유효성 검사
        if (result.hasErrors())
            return "addBook";

        // 파일 업로드 ==============================================================
        MultipartFile bookImage = book.getBookImage();

        String saveName = bookImage.getOriginalFilename();  // 전송받은 이미지 파일 이름을 얻는다.
        File saveFile = new File("C:\\upload", saveName);

        if (bookImage != null && !bookImage.isEmpty()) {

            try {
                bookImage.transferTo(saveFile);  // 도서 이미지 파일을 경로로 업로드한다.

            } catch (Exception e) {
                throw new RuntimeException("도서 이미지 업로드가 실패하였습니다", e);
            }
        }
        // 파일 업로드 끝 ===========================================================

        bookService.setNewBook(book);
        return "redirect:/books";
    }

    // 등록 페이지 제목 출력
    @ModelAttribute  // 메서드 수준의 @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록");
    }

    // 커스텀 데이터 바인딩
    // 도서 등록 페이지에서 입력된 모든 파라미터 값을 커맨드 객체로 바인딩
    // 저장소 객체에 신규 도서 정보를 저장한다.
    @InitBinder
    public void initBinder(WebDataBinder binder) {  // 바인딩할 커맨드 객체의 필드 이름 설정
        binder.setAllowedFields("bookId", "name", "unitPrice", "author",
                "description", "publisher", "category", "unitsInStock",
                "totalPages", "releaseDate", "condition", "bookImage");
    }

    // 예외 처리 : 잘못된 id로 검색을 시도한 경우
    @ExceptionHandler(value = {BookIdException.class})
    public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("invalidBookId", exception.getBookId());  // 요청한 도서 아이디 값 저장
        mav.addObject("exception", exception);  // 예외 처리 클래스 저장
        // 요청 URL과 요청 쿼리문 저장
        mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
        mav.setViewName("errorBook");

        return mav;
    }

}
