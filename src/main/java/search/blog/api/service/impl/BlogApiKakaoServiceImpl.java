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
import search.blog.api.entity.Search;
import search.blog.api.repository.SearchRepository;
import search.blog.api.service.BlogApiService;

@Service
@Primary
public class BlogApiKakaoServiceImpl implements BlogApiService {

    private final RestTemplate restTemplate;

    private final BlogApiKakaoConfig blogApiKakaoConfig;

    private final SearchRepository searchRepository;

    public BlogApiKakaoServiceImpl(RestTemplateBuilder restTemplateBuilder, BlogApiKakaoConfig blogApiKakaoConfig, SearchRepository searchRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.blogApiKakaoConfig = blogApiKakaoConfig;
        this.searchRepository = searchRepository;
    }

    /**
     * 카카오 블로그 검색 API를 호출하여 결과를 반환하고 검색어를 저장합니다.
     *
     * @param blogApiRequestDto 블로그 검색 API 요청에 필요한 데이터
     * @return BlogApiResponseDto 카카오 블로그 검색 API의 응답
     */
    @Override
    public BlogApiResponseDto callBlogListApi(BlogApiRequestDto blogApiRequestDto) {

        // 검색어 저장
        Search search = new Search();
        search.setQuery(blogApiRequestDto.getQuery());
        search.setSort(blogApiRequestDto.getSort());
        search.setPage(blogApiRequestDto.getPage());
        search.setSize(blogApiRequestDto.getSize());
        searchRepository.save(search);

        // Api 헤더 생성 후 요청
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", blogApiKakaoConfig.getApiKey());

        String url = blogApiKakaoConfig.getBaseUrl() + "?" + blogApiRequestDto.toString();

        ResponseEntity<BlogApiResponseDto> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BlogApiResponseDto.class);

        return responseEntity.getBody();
    }
}