package kr.co.chunjae.repository;

import kr.co.chunjae.domain.Book;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    // 도서 목록 출력
    List<Book> getAllBookList();  // 전체
    List<Book> getBookListByCategory(String category);  // 분야별 검색 결과 (단독 파라미터)
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);  // 분야별 검색 결과 (다중 파라미터)
    // 도서 상세 조회
    Book getBookById(String bookId);  // ID와 일치하는 도서 정보 출력
    // 도서 등록
    void setNewBook(Book book);
}
