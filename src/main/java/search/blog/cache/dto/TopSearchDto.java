package search.blog.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class TopSearchDto {
    private List<TopSearchQuery> topSearchQuery = new ArrayList<>();
}
