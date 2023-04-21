package search.blog.api.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Schema(name = "블로그")
public class Blog {
    @Schema(description = "블로그의 이름", example = "정란수의 브런치")
    private String blogname;
    @Schema(description = "블로그 글 요약", example = "이 점은 <b>집</b>을 지으면서 고민해보아야 한다. 하지만, 금액에 대한 가성비 대비 크게 문제되지 않을 부분이라 생각하여 설계로 극복하자고 생각했다. 전체 <b>집</b><b>짓기</b>의 기본방향은 크게 세 가지이다. 우선은 여가의 영역 증대이다. 현대 시대 일도 중요하지만, 여가시간 <b>집</b>에서 어떻게 보내느냐가 중요하니깐 이를 기본적...")
    private String contents;
    @Schema(description = "블로그 글 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]", example = "2017-05-07T18:50:07.000+09:00")
    private OffsetDateTime datetime;
    @Schema(description = "검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음", example = "http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc")
    private String thumbnail;
    @Schema(description = "블로그 글 제목", example = "정란수의 브런치")
    private String title;
    @Schema(description = "블로그 글 URL", example = "https://brunch.co.kr/@tourism/91")
    private String url;
}