package search.blog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("블로그 검색 Request")
public class BlogApiRequest {

    @NotBlank(message = "Query parameter is required")
    @ApiModelProperty(notes = "검색을 원하는 질의어", required = true, example = "카카오")
    private String query;

    @Pattern(regexp = "accuracy|recency", message = "Sort parameter must be 'accuracy' or 'recency'")
    @ApiModelProperty(notes = "결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy", required = false, example = "accuracy")
    private String sort;

    @Min(value = 1, message = "Size parameter must be between 1 and 50")
    @Max(value = 50, message = "Size parameter must be between 1 and 50")
    @ApiModelProperty(notes = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1", required = false, example = "1")
    private Integer page;

    @Min(value = 1, message = "Size parameter must be between 1 and 50")
    @Max(value = 50, message = "Size parameter must be between 1 and 50")
    @ApiModelProperty(notes = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10", required = false, example = "10")
    private Integer size;

    @Override
    public String toString() {
        return "query=" + query + "&sort=" + sort + "&page=" + page + "&size=" + size;
    }
}
