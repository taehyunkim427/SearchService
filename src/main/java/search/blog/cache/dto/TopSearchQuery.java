package search.blog.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class TopSearchQuery {
    private String query;
    private long cnt;
}