package search.blog.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import search.blog.cache.domain.PopularSearchQuery;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PopularSearch {
    private List<PopularSearchQuery> popularSearchQuery = new ArrayList<>();
}
