package kr.co.chunjae.service;

import kr.co.chunjae.domain.Book;
import kr.co.chunjae.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        return bookRepository.getBookListByCategory(category);
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        return bookRepository.getBookListByFilter(filter);
    }

    @Override
    public Book getBookById(String bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        return bookById;
    }

    @Override
    public void setNewBook(Book book) {
        bookRepository.setNewBook(book);
    }
}
