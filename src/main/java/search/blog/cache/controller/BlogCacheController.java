package search.blog.cache.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.cache.domain.PopularSearchQuery;
import search.blog.cache.dto.PopularSearchDto;
import search.blog.cache.service.BlogCacheService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog/cache")
@RequiredArgsConstructor
@Api(tags = "BlogSearchCache", value = "블로그 검색 캐시")
public class BlogCacheController {
    private final BlogCacheService blogCacheService;

    @GetMapping("/top")
    @ApiOperation(value = "블로그 Top 10", notes = "캐시에서 상위 10개의 검색어와 개수를 가져옵니다. 응답 바디는 query, cnt 구성된 JSON 객체입니다.")
    public PopularSearchDto callBlogListApi() {

        List<PopularSearchQuery> popularSearchQueries = blogCacheService.getPopularSearchQuery();
        PopularSearchDto popularSearchDto = new PopularSearchDto(popularSearchQueries);

        log.info("[BlogApiController] PopularSearchDto : " + popularSearchDto.toString());

        return popularSearchDto;
    }
}