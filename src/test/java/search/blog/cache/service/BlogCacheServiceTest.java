package search.blog.cache.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import search.blog.api.entity.Search;
import search.blog.api.repository.SearchRepository;
import search.blog.cache.dto.TopSearchQuery;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


@SpringBootTest
class BlogCacheServiceTest {

    @Autowired
    private BlogCacheService blogCacheService;

    @Autowired
    private SearchRepository searchRepository;

    private final Logger logger = LoggerFactory.getLogger(BlogCacheServiceTest.class);

    @Test
    public void testFindTop10Queries() {

        // Given
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                Search search = new Search();
                search.setQuery("카카오뱅크" + j);
                search.setSort("accuracy");
                search.setPage(1);
                search.setSize(10);
                searchRepository.save(search);
            }
        }

        Runtime.getRuntime().gc();

        // When
        long startSpace1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Instant startTime1 = Instant.now();

        blogCacheService.getTop10QueriesWithCountFromCache();
        List<TopSearchQuery> topQueries1 = blogCacheService.getTop10QueriesWithCountFromCache();

        Instant endTime1 = Instant.now();
        long endSpace1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long startSpace2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Instant startTime2 = Instant.now();

        blogCacheService.getTop10QueriesWithCountFromCache();
        List<TopSearchQuery> topQueries2 = blogCacheService.getTop10QueriesWithCountFromCache();

        Instant endTime2 = Instant.now();
        long endSpace2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Runtime.getRuntime().gc();

        long startSpace3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Instant startTime3 = Instant.now();

        blogCacheService.getTop10QueriesWithCountFromCache();
        List<TopSearchQuery> topQueries3 = blogCacheService.getTop10QueriesWithCountFromCache();

        Instant endTime3 = Instant.now();
        long endSpace3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Then
        logger.info("[blogCacheServiceTest] First : " + (endSpace1 - startSpace1) / 1024 / 1024 + " MB"
                + Duration.between(startTime1, endTime1).toMillis() + " ms");
        logger.info("[blogCacheServiceTest] Seconbd : " + (endSpace2 - startSpace2) / 1024 / 1024 + " MB"
                + Duration.between(startTime2, endTime2).toMillis() + " ms");
        logger.info("[blogCacheServiceTest] Third : " + (endSpace3 - startSpace3) / 1024 / 1024 + " MB"
                + Duration.between(startTime3, endTime3).toMillis() + " ms");

//        2023-03-22 19:42:20.292  INFO 44004 --- [    Test worker] s.b.cache.service.BlogCacheServiceTest   : [blogCacheServiceTest] First : 6 MB 90 ms
//        2023-03-22 19:42:20.293  INFO 44004 --- [    Test worker] s.b.cache.service.BlogCacheServiceTest   : [blogCacheServiceTest] Seconbd : 2 MB 12 ms
//        2023-03-22 19:42:20.293  INFO 44004 --- [    Test worker] s.b.cache.service.BlogCacheServiceTest   : [blogCacheServiceTest] Third : 2 MB 5 m

    }
}