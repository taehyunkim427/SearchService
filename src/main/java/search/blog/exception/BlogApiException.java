package search.blog.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BlogApiException extends RuntimeException {

    private final HttpStatus status;
    private final String error;
    private final String message;
    private final String path;
}
