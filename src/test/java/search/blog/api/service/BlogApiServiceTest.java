package search.blog.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlogApiServiceTest {

    @Autowired
    private BlogApiService blogApiService;

    @Test
    void setQueryAndSizeRequest() {
        // Given
        BlogApiRequestDto blogApiRequestDto = new BlogApiRequestDto();
        blogApiRequestDto.setQuery("Test");
        blogApiRequestDto.setSize(10);

        // When
        BlogApiResponseDto ret = blogApiService.callBlogListApi(blogApiRequestDto);

        // Then
        assertThat(ret.getMeta().getTotalCount()).isGreaterThan(0);
        assertThat(ret.getMeta().getIsEnd()).isEqualTo(false);
        assertThat(ret.getDocuments().size()).isEqualTo(10);
    }
}