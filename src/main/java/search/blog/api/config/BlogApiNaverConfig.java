package search.blog.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BlogApiNaverConfig {
    @Value("${blog.api.naver.key}")
    private String apiKey;

    @Value("${blog.api.naver.baseUrl}")
    private String baseUrl;

    @Value("${blog.api.naver.client}")
    private String client;
}