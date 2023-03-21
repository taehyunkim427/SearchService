package search.blog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("블로그 검색 Request")
public class BlogApiRequestDto {
    @ApiModelProperty(notes = "검색을 원하는 질의어", required = true, example = "카카오")
    private String query;
    @ApiModelProperty(notes = "결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy", required = false, example = "accuracy")
    private String sort;
    @ApiModelProperty(notes = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1", required = false, example = "1")
    private Integer page;
    @ApiModelProperty(notes = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10", required = false, example = "10")
    private Integer size;

    @Override
    public String toString() {
        return "query=" + query + "&sort=" + sort + "&page=" + page + "&size=" + size;
    }
}
