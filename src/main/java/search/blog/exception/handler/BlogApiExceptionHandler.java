//package search.blog.exception.handler;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import search.blog.api.service.BlogApiService;
//
//@ControllerAdvice("BlogApiController.class")
//public class BlogApiExceptionHandler {
//    private final BlogApiService blogApiService;
//
//    public BlogApiExceptionHandler(@Qualifier("naverApi") BlogApiService blogApiService) {
//        this.blogApiService = blogApiService;
//    }
//    ToDo. 네이버 api 후 메세지 파싱 리턴
//}