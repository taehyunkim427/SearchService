package search.blog.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Blog {
    private String blogname;

    private String contents;

    //[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    private OffsetDateTime datetime;

    private String thumbnail;

    private String title;

    private String url;
}
