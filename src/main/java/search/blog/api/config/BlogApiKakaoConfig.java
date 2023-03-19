package search.blog.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BlogApiKakaoConfig {
    @Value("${blog.api.kakao.key}")
    private String apiKey;

    @Value("${blog.api.kakao.baseUrl}")
    private String baseUrl;
}
