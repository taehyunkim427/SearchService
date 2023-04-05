package search.blog.cache.service;

import search.blog.cache.domain.TopSearchQuery;

import java.util.List;

public interface BlogCacheService {
    void addSearchQuery(String query);

    List<TopSearchQuery> getTop10PopularSearchQuery();
}

