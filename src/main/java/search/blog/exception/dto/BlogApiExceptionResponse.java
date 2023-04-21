package search.blog.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Error")
public class BlogApiExceptionResponse {
    @Schema(description = "오류 유형")
    private String errorType;
    @Schema(description = "오류 설명")
    private String message;
}
