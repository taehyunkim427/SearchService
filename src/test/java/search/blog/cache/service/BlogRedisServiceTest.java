package search.blog.cache.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import search.blog.api.entity.Search;
import search.blog.api.repository.SearchRepository;
import search.blog.cache.dto.TopSearchQuery;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BlogCacheServiceTest {

    @Autowired
    private BlogCacheService blogCacheService;

    @Autowired
    private SearchRepository searchRepository;

    @Test
    public void testFindTop10Queries() {
        // Given
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Search search = new Search();
                search.setQuery("카카오뱅크" + j);
                search.setSort("accuracy");
                search.setPage(1);
                search.setSize(10);
                searchRepository.save(search);
            }
        }

        // When
        blogCacheService.getTop10QueriesWithCountFromCache();
        List<TopSearchQuery> topQueries = blogCacheService.getTop10QueriesWithCountFromCache();
        System.out.println(topQueries.toString());

    }
}