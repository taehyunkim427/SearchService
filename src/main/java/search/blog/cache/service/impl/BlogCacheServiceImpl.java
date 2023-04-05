package search.blog.cache.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import search.blog.cache.domain.TopSearchQuery;
import search.blog.cache.service.BlogCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogCacheServiceImpl implements BlogCacheService {
    private static final String POPULAR_SEARCH_KEY = "popular_search";
    private final RedisTemplate<String, Object> redisTemplate;

    // 검색어를 저장하고 점수를 증가시킵니다.
    public void addSearchQuery(String Query) {
        redisTemplate.opsForZSet().incrementScore(POPULAR_SEARCH_KEY, Query, 1);
    }

    // 상위 10개 인기 검색어를 조회합니다.
    public List<TopSearchQuery> getTop10PopularSearchQuery() {
        Set<ZSetOperations.TypedTuple<Object>> resultSet =
                redisTemplate.opsForZSet().reverseRangeWithScores(POPULAR_SEARCH_KEY, 0, 9);

        List<TopSearchQuery> result = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> tuple : resultSet) {
            result.add(new TopSearchQuery((String) tuple.getValue(), tuple.getScore().longValue()));
        }

        return result;
    }

}
