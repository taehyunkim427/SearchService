package search.blog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import search.blog.api.domain.Blog;
import search.blog.api.domain.BlogMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogApiResponseDto {
    private BlogMeta meta;
    private List<Blog> documents = new ArrayList<>();
}
