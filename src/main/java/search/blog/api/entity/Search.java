package search.blog.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Search {
    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    @Column(nullable = false)
    private String query;

    private String sort;

    private Integer page;

    private Integer size;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;
}
