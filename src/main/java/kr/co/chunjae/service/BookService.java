package kr.co.chunjae.service;

import kr.co.chunjae.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    List<Book> getAllBookList();  // 도서 목록 출력
    List<Book> getBookListByCategory(String category);  // 분야별 검색 결과 (단독 파라미터)
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);  // 분야별 검색 결과 (다중 파라미터)
    Book getBookById(String bookId);  // ID와 일치하는 도서 정보 출력
    void setNewBook(Book book);  // 도서 등록
}

