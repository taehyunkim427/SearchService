package search.blog.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogApiNaverResponseDto {
    private RssContainer rss;

    @Data
    public static class RssContainer {
        private Channel channel;
    }

    @Data
    public static class Channel {
        private String title;
        private String link;
        private String description;
        private LocalDateTime lastBuildDate;
        private Integer total;
        private Integer start;
        private Integer display;
        private List<Item> item;
    }

    @Data
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private LocalDateTime postdate;
    }
}