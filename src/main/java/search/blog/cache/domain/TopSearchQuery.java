package search.blog.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopSearchQuery {
    private String query;
    private long cnt;
}