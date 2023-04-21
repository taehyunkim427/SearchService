package search.blog.cache.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Schema(name = "인기 검색어")
public class PopularSearch implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Schema(description = "인기 검색어", example = "카카오뱅크")
    private String query;

    @Column(nullable = false)
    @Schema(description = "검색된 횟수", example = "10")
    private long cnt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;
}