package kr.co.chunjae.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class BookIdException extends RuntimeException {

    private String bookId;

    public BookIdException(String bookId) {  // 생성자
        this.bookId = bookId;
    }
}
