package search.blog.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import search.blog.api.service.BlogApiService;
import search.blog.exception.BlogApiException;

@ControllerAdvice
public class BlogApiExceptionHandler {
    private BlogApiService blogApiService;

    public BlogApiExceptionHandler(BlogApiService blogApiService) {
        this.blogApiService = blogApiService;
    }

    @ExceptionHandler(BlogApiException.class)
    public void handleSampleException(BlogApiException ex) {
        // TODO: 예외 처리 로직
        // 새로운 구현 클래스로 대체하기 위해 blogApiService의 구현체를 변경
    }
}