//package search.blog.api.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import search.blog.api.config.BlogApiNaverConfig;
//import search.blog.api.dto.BlogApiNaverRequestDto;
//import search.blog.api.dto.BlogApiRequestDto;
//import search.blog.api.dto.BlogApiResponseDto;
//import search.blog.api.entity.Search;
//import search.blog.api.repository.SearchRepository;
//import search.blog.api.service.BlogApiService;
//
//@Service
//@Qualifier("naverApi")
//@RequiredArgsConstructor
//public class BlogApiNaverServiceImpl implements BlogApiService {
//
//    private final SearchRepository searchRepository;
//
//    private final RestTemplate restTemplate;
//
//    private final BlogApiNaverConfig blogApiNaverConfig;
//
//    @Override
//    public BlogApiResponseDto callBlogListApi(BlogApiRequestDto blogApiRequestDto) {
//
//        BlogApiNaverRequestDto naverRequestDto = convertToNaverRequestDto(blogApiRequestDto);
//
//        saveSearchTerm(blogApiRequestDto);
//
//        // Api 헤더 생성 후 요청
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", blogApiNaverConfig.getApiKey());
//        headers.set("X-Naver-Client-Id", blogApiNaverConfig.getApiKey());
//        headers.set("X-Naver-Client-Secret", blogApiNaverConfig.getClient());
//
//        String url = blogApiNaverConfig.getBaseUrl() + "?" + blogApiRequestDto.toString();
//        ResponseEntity<BlogApiResponseDto> responseEntity =
//                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BlogApiResponseDto.class);
//        return responseEntity.getBody();
//    }
//
//    private BlogApiNaverRequestDto convertToNaverRequestDto(BlogApiRequestDto dto) {
//        BlogApiNaverRequestDto naverDto = new BlogApiNaverRequestDto();
//        naverDto.setQuery(dto.getQuery());
//        naverDto.setSort(dto.getSort());
//        naverDto.setStart(dto.getPage());
//        naverDto.setDisplay(dto.getSize());
//        return naverDto;
//    }
//
//    private void saveSearchTerm(BlogApiRequestDto blogApiRequestDto) {
//        // 검색어 저장
//        Search search = new Search();
//        search.setQuery(blogApiRequestDto.getQuery());
//        search.setSort(blogApiRequestDto.getSort());
//        search.setPage(blogApiRequestDto.getPage());
//        search.setSize(blogApiRequestDto.getSize());
//        searchRepository.save(search);
//    }
//}
