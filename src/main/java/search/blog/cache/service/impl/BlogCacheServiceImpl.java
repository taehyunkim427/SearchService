package search.blog.cache.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import search.blog.cache.domain.PopularSearch;
import search.blog.cache.repository.PopularSearchRepository;
import search.blog.cache.service.BlogCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogCacheServiceImpl implements BlogCacheService {
    private static final String POPULAR_SEARCH_KEY = "popular_search";

    private final RedisTemplate<String, Object> redisTemplate;

    private final PopularSearchRepository popularSearchRepository;

    // 검색어를 저장하고 점수를 증가
    public void addPopularSearch(String Query) {
        redisTemplate.opsForZSet().incrementScore(POPULAR_SEARCH_KEY, Query, 1);
    }

    // 상위 10개 인기 검색어를 조회
    public List<PopularSearch> getPopularSearch() {
        Set<ZSetOperations.TypedTuple<Object>> resultSet =
                redisTemplate.opsForZSet().reverseRangeWithScores(POPULAR_SEARCH_KEY, 0, 9);

        List<PopularSearch> result = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> tuple : resultSet) {
            result.add(PopularSearch.builder()
                    .query((String) tuple.getValue())
                    .cnt(tuple.getScore().longValue())
                    .build());
        }

        return result;
    }

    // 1분마다 인기 검색어를 H2 데이터베이스에 백업
    @Scheduled(fixedRate = 60000)
    public void backUpPopularSearch() {
        // 1. Redis에서 인기 검색어 조회
        List<PopularSearch> popularSearchQueries = getPopularSearch();

        // 2. 기존 인기 검색어를 H2 데이터베이스에서 삭제
        popularSearchRepository.deleteAll();

        // 3. 인기 검색어를 H2 데이터베이스에 백업
        popularSearchRepository.saveAll(popularSearchQueries);
    }
}
