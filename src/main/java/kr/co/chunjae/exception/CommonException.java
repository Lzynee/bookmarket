package kr.co.chunjae.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice  // 전역 예외 처리 (모든 컨트롤러의 예외 처리)
public class CommonException {
    @ExceptionHandler(RuntimeException.class)
    private ModelAndView handleErrorCommon(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("errorCommon");

        return modelAndView;
    }
}
