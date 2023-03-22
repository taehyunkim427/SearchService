package search.blog.cache.service;

import search.blog.cache.dto.TopSearchQuery;

import java.util.List;

public interface BlogCacheService {
    List<TopSearchQuery> getTop10QueriesWithCountFromCache();
}

