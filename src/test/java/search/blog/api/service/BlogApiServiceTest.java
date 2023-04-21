package search.blog.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import search.blog.api.domain.dto.BlogApiRequest;
import search.blog.api.domain.dto.BlogApiResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlogApiServiceTest {

    @Autowired
    private BlogApiService blogApiService;

    @Test
    void setQueryAndSizeRequest() {
        // Given
        BlogApiRequest blogApiRequest = BlogApiRequest.builder()
                .query("카카오뱅크")
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();

        // When
        BlogApiResponse ret = blogApiService.callApi(blogApiRequest);

        // Then
        assertThat(ret.getMeta().getTotalCount()).isGreaterThan(0);
        assertThat(ret.getMeta().getIsEnd()).isEqualTo(false);
        assertThat(ret.getDocuments().size()).isEqualTo(10);
    }
}