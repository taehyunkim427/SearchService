package search.blog.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;
import search.blog.api.service.BlogApiService;

@RestController
@RequestMapping("/blog/api")
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogApiService blogApiService;

    @GetMapping("/list")
    public BlogApiResponseDto callBlogListApi(@RequestBody BlogApiRequestDto blogApiRequestDto) {
        return blogApiService.callBlogListApi(blogApiRequestDto);
    }
}
