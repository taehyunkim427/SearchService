package search.blog.cache.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import search.blog.cache.domain.entity.PopularSearch;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Schema(name = "인기 검색어 리스트 Response")
public class PopularSearchResponse {
    @Schema(description = "인기 검색어")
    private List<PopularSearch> popularSearch = new ArrayList<>();
}
