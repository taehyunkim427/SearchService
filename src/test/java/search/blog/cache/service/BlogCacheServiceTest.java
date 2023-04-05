package search.blog.cache.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import search.blog.cache.domain.TopSearchQuery;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BlogCacheServiceTest {

    @Autowired
    private BlogCacheService blogCacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    @EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "local")
    public void setUp() {
        // 테스트 전에 Redis에 저장된 모든 데이터를 삭제
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "local")
    public void testAddSearchQueryAndTop10PopularSearchQuery() {
        // Given : 검색어를 저장하고 점수를 증가
        blogCacheService.addSearchQuery("kakao");
        blogCacheService.addSearchQuery("kakao");
        blogCacheService.addSearchQuery("kakao");
        blogCacheService.addSearchQuery("naver");
        blogCacheService.addSearchQuery("naver");
        blogCacheService.addSearchQuery("toss");

        // When : 상위 10개 인기 검색어를 조회
        List<TopSearchQuery> topSearchQueries = blogCacheService.getTop10PopularSearchQuery();

        // Then : 결과를 검증
        assertThat(topSearchQueries).hasSize(3);
        assertThat(topSearchQueries.get(0).getQuery()).isEqualTo("kakao");
        assertThat(topSearchQueries.get(0).getCnt()).isEqualTo(3);
        assertThat(topSearchQueries.get(1).getQuery()).isEqualTo("naver");
        assertThat(topSearchQueries.get(1).getCnt()).isEqualTo(2);
        assertThat(topSearchQueries.get(2).getQuery()).isEqualTo("toss");
        assertThat(topSearchQueries.get(2).getCnt()).isEqualTo(1);
    }
}