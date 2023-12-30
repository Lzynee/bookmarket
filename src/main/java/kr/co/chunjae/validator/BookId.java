package kr.co.chunjae.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Constraint(validatedBy = BookIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BookId {
    // @BookId에 대한 유효성 검사를 할 때 오류가 발생하면
    // 메시지 리소스 파일에 설정된 BookId.NewBook.bookId의 메시지를 출력한다.
    String message() default "";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
