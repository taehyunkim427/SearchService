package search.blog.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import search.blog.cache.domain.PopularSearchQuery;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PopularSearchDto {
    private List<PopularSearchQuery> popularSearchQuery = new ArrayList<>();
}
