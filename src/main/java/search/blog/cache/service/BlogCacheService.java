package search.blog.cache.service;

import search.blog.cache.domain.entity.PopularSearch;

import java.util.List;

public interface BlogCacheService {
    // 검색어를 저장하고 점수를 증가
    void addPopularSearch(String query);

    // 상위 10개 인기 검색어를 조회
    List<PopularSearch> getPopularSearch();

    // 인기 검색어를 H2 데이터베이스에 백업
    void backUpPopularSearch();
}

