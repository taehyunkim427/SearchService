package search.blog.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;
import search.blog.api.service.BlogApiService;
import search.blog.cache.service.BlogCacheService;


@RestController
@RequestMapping("/blog/api")
@RequiredArgsConstructor
@Api(tags = "BlogSearchApi", value = "블로그 검색 Api")
public class BlogApiController {

    private final BlogApiService blogApiService;
    private final BlogCacheService blogCacheService;
    private final Logger logger = LoggerFactory.getLogger(BlogApiController.class);

    @PostMapping("/list")
    @ApiOperation(value = "블로그 검색 Api", notes = "다음 블로그 검색 서비스에서 질의어로 게시물을 검색합니다. 원하는 검색어와 함께 결과 형식 파라미터를 선택적으로 추가할 수 있습니다. 응답 바디는 meta, documents로 구성된 JSON 객체입니다.")
    public BlogApiResponseDto callBlogListApi(@ApiParam(value = "query", required = true)  @RequestBody BlogApiRequestDto blogApiRequestDto) {
        logger.info("[BlogApiController] blogApiRequestDto : " + blogApiRequestDto.toString());

        // 1. Kakao API 호출
        BlogApiResponseDto blogApiResponseDto = blogApiService.callBlogListApi(blogApiRequestDto);

        // 2. H2 DB에 검색 엔티티 저장
        blogApiService.saveSearchTerm(blogApiRequestDto);

        // 3. Redis 캐시에 쿼리 카운트 증가
        blogCacheService.addSearchQuery(blogApiRequestDto.getQuery());

        logger.info("[BlogApiController] blogApiResponseDto : " + blogApiResponseDto.getMeta().toString());
        return blogApiResponseDto;
    }
}
