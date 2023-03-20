package search.blog.api.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import search.blog.api.dto.BlogApiRequestDto;
import search.blog.api.dto.BlogApiResponseDto;
import search.blog.api.service.BlogApiService;

@RestController
@RequestMapping("/blog/api")
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogApiService blogApiService;
    private final Logger logger = LoggerFactory.getLogger(BlogApiController.class);

    @PostMapping("/list")
    public BlogApiResponseDto callBlogListApi(@RequestBody BlogApiRequestDto blogApiRequestDto) {
        logger.info("[BlogApiController] blogApiRequestDto : " + blogApiRequestDto.toString());
        BlogApiResponseDto blogApiResponseDto = blogApiService.callBlogListApi(blogApiRequestDto);
        logger.info("[BlogApiController] Kakao Api retrieve blog total count : " + blogApiResponseDto.getMeta().getTotalCount());
        return blogApiResponseDto;
    }
}
