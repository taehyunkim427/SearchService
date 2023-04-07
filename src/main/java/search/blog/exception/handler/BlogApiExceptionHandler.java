package search.blog.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import search.blog.exception.dto.BlogApiExceptionResponse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ControllerAdvice
public class BlogApiExceptionHandler extends ResponseEntityExceptionHandler {

    // ';' 전까지의 문자열 추출
    public static String extractTextBeforeSemicolon(String input) {
        Pattern pattern = Pattern.compile("^(.*?);");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return input;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = extractTextBeforeSemicolon(ex.getMessage());
        BlogApiExceptionResponse blogApiExceptionResponse = BlogApiExceptionResponse.builder()
                .errorType("BAD_REQUEST")
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(blogApiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        String responseBody = ex.getResponseBodyAsString();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BlogApiExceptionResponse blogApiExceptionResponse = BlogApiExceptionResponse.builder()
                .errorType("METHOD_NOT_ALLOWED")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(blogApiExceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BlogApiExceptionResponse blogApiExceptionResponse = BlogApiExceptionResponse.builder()
                .errorType("UNSUPPORTED_MEDIA_TYPE")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(blogApiExceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        BlogApiExceptionResponse blogApiExceptionResponse = BlogApiExceptionResponse.builder()
                .errorType("VALIDATION_ERROR")
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(blogApiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}