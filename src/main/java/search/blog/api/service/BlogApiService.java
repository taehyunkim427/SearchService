package search.blog.api.service;

import search.blog.api.dto.BlogApiRequest;
import search.blog.api.dto.BlogApiResponse;

public interface BlogApiService {
    // 카카오 블로그 검색 API를 호출하여 결과를 반환
    BlogApiResponse callApi(BlogApiRequest blogApiRequest);

    void saveSearch(BlogApiRequest blogApiRequest);
}
