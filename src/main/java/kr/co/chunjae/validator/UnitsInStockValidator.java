package kr.co.chunjae.validator;

import kr.co.chunjae.domain.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component
public class UnitsInStockValidator implements Validator {

    public boolean supports(Class<?> clazz) {  // Book 클래스의 유효성 검사 여부를 위한 메서드
        return Book.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {  // Book 클래스의 유효성 검사 메서드

        Book book = (Book) target;
        // 도서 가격이 1만원 이상이고, 재고가 99권을 초과하면 유효성 검사를 할 때 오류 발생
        if (book.getUnitPrice() >= 10000 && book.getUnitsInStock() > 99) {
            // 오류 객체의 속성과 메시지 저장
            errors.rejectValue("unitsInStock", "UnitsInStockValidator.message");
        }
    }
}
