package search.blog.api.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import search.blog.api.config.BlogApiKakaoConfig;
import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;
import search.blog.api.service.BlogApiService;

@Service
@Primary
public class BlogApiKakaoServiceImpl implements BlogApiService {
    private final RestTemplate restTemplate;
    private final BlogApiKakaoConfig blogApiKakaoConfig;

    public BlogApiKakaoServiceImpl(RestTemplateBuilder restTemplateBuilder, BlogApiKakaoConfig blogApiKakaoConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.blogApiKakaoConfig = blogApiKakaoConfig;
    }
    @Override
    public BlogApiResponseDto callBlogListApi(BlogApiRequestDto blogApiRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", blogApiKakaoConfig.getApiKey());

        String url = blogApiKakaoConfig.getBaseUrl() + "?" + blogApiRequestDto.toString();

        ResponseEntity<BlogApiResponseDto> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BlogApiResponseDto.class);

        return responseEntity.getBody();
    }
}