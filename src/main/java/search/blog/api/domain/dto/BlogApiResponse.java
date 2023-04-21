package search.blog.api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import search.blog.api.domain.value.Blog;
import search.blog.api.domain.value.BlogMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "블로그 검색 Response")
public class BlogApiResponse {
    @Schema(description = "블로그 검색 부가 정보")
    private BlogMeta meta;

    @Builder.Default
    @Schema(description = "블로그 리스트")
    private List<Blog> documents = new ArrayList<>();
}