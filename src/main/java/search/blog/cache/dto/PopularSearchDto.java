package search.blog.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import search.blog.cache.domain.PopularSearch;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PopularSearchDto {
    private List<PopularSearch> popularSearch = new ArrayList<>();
}
