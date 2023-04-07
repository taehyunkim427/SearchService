package search.blog.cache.service;

import search.blog.cache.domain.PopularSearchQuery;

import java.util.List;

public interface BlogCacheService {
    void addPopularSearchQuery(String query);

    List<PopularSearchQuery> getPopularSearchQuery();
}

