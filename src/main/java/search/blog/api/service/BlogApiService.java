package search.blog.api.service;

import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;

public interface BlogApiService {
    BlogApiResponseDto callBlogListApi(BlogApiRequestDto blogApiRequestDto);

    void saveSearchTerm(BlogApiRequestDto blogApiRequestDto);
}
