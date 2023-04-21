package search.blog.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.api.domain.dto.BlogApiRequest;
import search.blog.api.domain.dto.BlogApiResponse;
import search.blog.api.service.BlogApiService;
import search.blog.cache.service.BlogCacheService;
import search.blog.exception.dto.BlogApiExceptionResponse;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Tag(name = "BlogSearchApi", description = "블로그 검색 Api")
public class BlogApiController {

    private final BlogApiService blogApiService;
    private final BlogCacheService blogCacheService;

    @PostMapping("/list")
    @Operation(summary = "블로그 검색 Api", description = "다음 블로그 검색 서비스에서 질의어로 게시물을 검색합니다. 원하는 검색어와 함께 결과 형식 파라미터를 선택적으로 추가할 수 있습니다. 응답 바디는 meta, documents로 구성된 JSON 객체입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = BlogApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class))),
            @ApiResponse(responseCode = "415", description = "지원되지 않는 미디어 유형", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class)))
    })
    public BlogApiResponse callBlogListApi(@Parameter(description = "query", required = true, schema = @Schema(implementation = BlogApiRequest.class)) @Valid @RequestBody BlogApiRequest blogApiRequest) {

        log.info("[BlogApiController] blogApiRequestDto : " + blogApiRequest.toString());

        // 1. Kakao API 호출
        BlogApiResponse blogApiResponse = blogApiService.callApi(blogApiRequest);

        // 2. H2 DB에 검색 엔티티 저장
        blogApiService.saveSearch(blogApiRequest);

        // 3. Redis 캐시에 쿼리 카운트 증가
        blogCacheService.addPopularSearch(blogApiRequest.getQuery());

        log.info("[BlogApiController] blogApiResponseDto : " + blogApiResponse.getMeta().toString());

        return blogApiResponse;
    }
}