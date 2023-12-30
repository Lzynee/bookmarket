package kr.co.chunjae.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MonitoringInterceptor implements HandlerInterceptor {

    ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    // 컨트롤러를 호출하기 전에 실행되는
    // HandlerInterceptor 인터페이스 메서드
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());
        stopWatchLocal.set(stopWatch);

        logger.info("접근한 URL 경로 : " + getURLPath(request));
        logger.info("요청 처리 시작 시각 : " + getCurrentTime());
        return true;
    }
    // 컨트롤러를 호출하여 처리한 후에 실행된다.
    public void postHandle(HttpServletRequest arg0,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        logger.info("요청 처리 종료 시각 : " + getCurrentTime());
    }
    // 뷰에 최종 결과를 반환한 후에 실행
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception exception) throws Exception {

        StopWatch stopWatch = stopWatchLocal.get();
        stopWatch.stop();

        logger.info("요청 처리 소요 시간 : " + stopWatch.getTotalTimeMillis() + "ms");
        stopWatchLocal.set(null);
        logger.info("=====================================================");
    }

    // 요청 URL과 쿼리문을 얻어 온다.
    private String getURLPath(HttpServletRequest request) {
        String currentPath = request.getRequestURI();
        String queryString = request.getQueryString();

        queryString = queryString == null ? "" : "?" + queryString;
        return currentPath + queryString;
    }

    // 현재 년/월/일 시:분:초를 얻어 온다.
    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return formatter.format(calendar.getTime());
    }
}
