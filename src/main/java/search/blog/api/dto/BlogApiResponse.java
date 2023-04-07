package search.blog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import search.blog.api.domain.Blog;
import search.blog.api.domain.BlogMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("블로그 검색 Response")
public class BlogApiResponse {
    @ApiModelProperty("블로그 검색 부가 정보")
    private BlogMeta meta;

    @Builder.Default
    @ApiModelProperty(notes = "블로그 리스트")
    private List<Blog> documents = new ArrayList<>();
}
