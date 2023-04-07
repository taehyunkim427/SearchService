package search.blog.api.service;

import search.blog.api.dto.BlogApiRequest;
import search.blog.api.dto.BlogApiResponse;

public interface BlogApiService {
    BlogApiResponse callBlogListApi(BlogApiRequest blogApiRequest);

    void saveSearchTerm(BlogApiRequest blogApiRequest);
}
