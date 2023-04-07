package search.blog.cache.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import search.blog.cache.domain.PopularSearch;
import search.blog.cache.repository.PopularSearchRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BlogCacheServiceTest {

    @Autowired
    private BlogCacheService blogCacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PopularSearchRepository popularSearchRepository;

    @BeforeEach
    @EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "local")
    public void setUp() {
        // 테스트 전 Redis에 저장된 모든 데이터 삭제
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "local")
    public void testAddSearchQueryAndTop10PopularSearchQuery() {
        // Given : 검색어를 저장하고 점수 증가
        blogCacheService.addPopularSearch("kakao");
        blogCacheService.addPopularSearch("kakao");
        blogCacheService.addPopularSearch("kakao");
        blogCacheService.addPopularSearch("naver");
        blogCacheService.addPopularSearch("naver");
        blogCacheService.addPopularSearch("toss");

        // When : 상위 10개 인기 검색어를 조회
        List<PopularSearch> popularSearch = blogCacheService.getPopularSearch();

        // Then : 결과를 검증
        assertThat(popularSearch).hasSize(3);
        assertThat(popularSearch.get(0).getQuery()).isEqualTo("kakao");
        assertThat(popularSearch.get(0).getCnt()).isEqualTo(3);
        assertThat(popularSearch.get(1).getQuery()).isEqualTo("naver");
        assertThat(popularSearch.get(1).getCnt()).isEqualTo(2);
        assertThat(popularSearch.get(2).getQuery()).isEqualTo("toss");
        assertThat(popularSearch.get(2).getCnt()).isEqualTo(1);
    }

    @Test
    public void testSynchronizePopularSearchToH2() {
        // 1. Redis에서 인기 검색어 조회
        List<PopularSearch> popularSearchQueriesFromRedis = blogCacheService.getPopularSearch();

        // 2. 인기 검색어 동기화 메소드 실행
        blogCacheService.backUpPopularSearch();

        // 3. H2 데이터베이스에서 인기 검색어 조회
        List<PopularSearch> popularSearchQueriesFromH2 = popularSearchRepository.findAll();

        // 4. Redis에서 가져온 인기 검색어와 H2 데이터베이스에서 가져온 인기 검색어 비교
        assertThat(popularSearchQueriesFromH2)
                .hasSize(popularSearchQueriesFromRedis.size())
                .usingElementComparatorIgnoringFields("id")
                .containsAll(popularSearchQueriesFromRedis);
    }
}