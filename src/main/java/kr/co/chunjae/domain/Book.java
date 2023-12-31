package kr.co.chunjae.domain;

import kr.co.chunjae.validator.BookId;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

// JSR-380 애너테이션 패키지 임포트
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Book {

    @Pattern(regexp="ISBN[1-9]+", message = "{Pattern}")  // bookId의 제약 사항: 첫 문자가 ISBN으로 시작하여 1부터 9까지 연속된 숫자가 오는 정규 표현식
    // 유효성 검사 시 bookId값이 패턴과 일치하지 않으면 메시지 리소스 파일에 선언된 Pattern 메시지를 출력한다.
    @BookId
    private String bookId;     // 도서ID

    @Size(min=4, max = 50, message = "{Size}")  // name의 제약 사항: 최소 4자 이상, 최대 50자 이하의 문자열 크기
    private String name;      // 도서명

    // unitPrice의 제약 사항 : 최솟값, 자릿수(정수 8자리, 소수점 2자리), Null이 아닌 값
    @Min(value = 0, message = "{Min}")
    @Digits(integer = 8, fraction = 2, message = "{Digits}")
    @NotNull(message = "{NotNull}")
    private BigDecimal unitPrice;      // 가격

    private String author;     // 저자
    private String description; // 설명
    private String publisher;   // 출판사
    private String category;    // 분류
    private long unitsInStock;  // 재고수
    private String releaseDate; // 출판일(월/년)
    private String condition;   // 신규 도서 or 중고 도서 or 전자책

    private MultipartFile bookImage;  // 도서 이미지

    // 기본 생성자
    public Book() {
        super();
    }
    // 일반 생성자
    public Book(String bookId, String name, BigDecimal unitPrice) {
        super();
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
    }
}
