package search.blog.api.domain.value;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(name = "블로그 검색 부가 정보")
public class BlogMeta {
    @Schema(description = "현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음", example = "true")
    private Boolean isEnd;
    @Schema(description = "total_count 중 노출 가능 문서 수", example = "5")
    private Integer pageableCount;
    @Schema(description = "검색된 문서 수", example = "5")
    private Integer totalCount;
}