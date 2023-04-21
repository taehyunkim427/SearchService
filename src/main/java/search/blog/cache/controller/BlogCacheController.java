package search.blog.cache.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.cache.domain.entity.PopularSearch;
import search.blog.cache.domain.dto.PopularSearchResponse;
import search.blog.cache.service.BlogCacheService;
import search.blog.exception.dto.BlogApiExceptionResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog/cache")
@RequiredArgsConstructor
@Tag(name = "BlogSearchCache", description = "블로그 검색 캐시")
public class BlogCacheController {
    private final BlogCacheService blogCacheService;

    @GetMapping("/hype")
    @Operation(summary = "블로그 인기검색어", description = "캐싱 된 상위 10개의 인기 검색어와 조회 수를 가져옵니다. 응답은 query, cnt로 구성된 JSON 객체입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인기 검색어 조회 성공", content = @Content(schema = @Schema(implementation = PopularSearchResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class))),
            @ApiResponse(responseCode = "415", description = "지원되지 않는 미디어 유형", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류", content = @Content(schema = @Schema(implementation = BlogApiExceptionResponse.class)))
    })
    public PopularSearchResponse getPopularSearch() {

        List<PopularSearch> popularSearchQueries = blogCacheService.getPopularSearch();
        PopularSearchResponse popularSearchResponse = new PopularSearchResponse(popularSearchQueries);

        log.info("[BlogCacheController] PopularSearchResponse : " + popularSearchResponse.toString());

        return popularSearchResponse;
    }
}