package kr.co.chunjae.validator;

import kr.co.chunjae.domain.Book;
import kr.co.chunjae.exception.BookIdException;
import kr.co.chunjae.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookIdValidator implements ConstraintValidator<BookId, String> {

    @Autowired
    private BookService bookService;

    public void initialize(BookId constraintAnnotation) {  // @BookId 정보 초기화 메서드

    }

    // 유효성 검사 메서드
    // Book의 bookId 속성 값을 읽어 유효성 검사를 수행한다.
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Book book;

        try {
            // bookService.getBookById() 메서드로 입력된 도서 ID가 이미 있다면 예외 처리 발생
            book = bookService.getBookById(value);

        } catch (BookIdException e) {
            return true;
        }

        if (book != null) {
            return false;
        }

        return true;
    }
}
