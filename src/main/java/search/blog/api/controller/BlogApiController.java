package search.blog.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.api.dto.BlogApiRequest;
import search.blog.api.dto.BlogApiResponse;
import search.blog.api.service.BlogApiService;
import search.blog.cache.service.BlogCacheService;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/blog/api")
@RequiredArgsConstructor
@Api(tags = "BlogSearchApi", value = "블로그 검색 Api")
public class BlogApiController {

    private final BlogApiService blogApiService;
    private final BlogCacheService blogCacheService;

    @PostMapping("/list")
    @ApiOperation(value = "블로그 검색 Api", notes = "다음 블로그 검색 서비스에서 질의어로 게시물을 검색합니다. 원하는 검색어와 함께 결과 형식 파라미터를 선택적으로 추가할 수 있습니다. 응답 바디는 meta, documents로 구성된 JSON 객체입니다.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = BlogApiResponse.class)))})
    public BlogApiResponse callBlogListApi(@ApiParam(value = "query", required = true) @Valid @RequestBody BlogApiRequest blogApiRequest) {

        log.info("[BlogApiController] blogApiRequestDto : " + blogApiRequest.toString());

        // 1. Kakao API 호출
        BlogApiResponse blogApiResponse = blogApiService.callBlogListApi(blogApiRequest);

        // 2. H2 DB에 검색 엔티티 저장
        blogApiService.saveSearchTerm(blogApiRequest);

        // 3. Redis 캐시에 쿼리 카운트 증가
        blogCacheService.addPopularSearchQuery(blogApiRequest.getQuery());

        log.info("[BlogApiController] blogApiResponseDto : " + blogApiResponse.getMeta().toString());

        return blogApiResponse;
    }
}
