package search.blog.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import search.blog.api.dto.BlogApiRequest;
import search.blog.api.dto.BlogApiResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlogApiControllerTest {

    @Autowired
    private BlogApiController blogApiController;

    @Test
    @EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "local")
    void setQueryAndSizeRequest() {
        // Given
        BlogApiRequest blogApiRequest = BlogApiRequest.builder()
                .query("카카오뱅크")
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();

        // When
        BlogApiResponse ret = blogApiController.callBlogListApi(blogApiRequest);

        // Then
        assertThat(ret.getMeta().getTotalCount()).isGreaterThan(0);
        assertThat(ret.getMeta().getIsEnd()).isEqualTo(false);
        assertThat(ret.getDocuments().size()).isEqualTo(10);
    }
}