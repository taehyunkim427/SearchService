package search.blog.cache.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import search.blog.api.repository.SearchRepository;
import search.blog.cache.dto.TopSearchQuery;
import search.blog.cache.service.BlogCacheService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogCacheServiceImpl implements BlogCacheService {
    private final SearchRepository searchRepository;

    /**
     * 캐시 또는 데이터베이스에서 검색어 개수 상위 10개의 목록을 조회합니다.
     *
     * @return 상위 10개 검색어와 검색어의 개수를 포함하는 {@link TopSearchQuery} 객체 목록
     * 동작 방식:
     * 1. "Search" 캐시에서 "Top" 키를 사용하여 저장된 결과를 찾습니다.
     * 2. 저장된 결과가 존재하면, 그 결과를 반환합니다.
     * 3. 저장된 결과가 없으면, 메서드가 실행되고 결과가 캐시에 "Top" 키로 저장되고 반환됩니다.
     * 캐시된 결과는 작성된 후 30초 동안만 유효 (apllication.yml에서 수정)
     * spring.cache.caffeine.spec: expireAfterWrite=30s
     *
     * web server에서 nuxtServerInit Lyfecycle 훅의 스케쥴링을 통해 10초마다 요청을 보내고 있습니다.
     */
    @Cacheable(value = "Search", key = "Top")
    public List<TopSearchQuery> getTop10QueriesWithCountFromCache() {
        List<Object[]> topQueriesWithCount = searchRepository.getTop10QueriesWithCount();
        List<TopSearchQuery> topSearchQueries = topQueriesWithCount.stream()
                .map(result -> new TopSearchQuery((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());
        return topSearchQueries;

    }

}
