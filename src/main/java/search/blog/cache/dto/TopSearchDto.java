package search.blog.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import search.blog.cache.domain.TopSearchQuery;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TopSearchDto {
    private List<TopSearchQuery> topSearchQuery = new ArrayList<>();
}
