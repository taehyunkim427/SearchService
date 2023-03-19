package search.blog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogApiRequestDto {
    private String query;
    private String sort;
    private Integer page;
    private Integer size;

    @Override
    public String toString() {
        return "query=" + query + "&sort=" + sort + "&page=" + page + "&size=" + size + "";
    }
}
