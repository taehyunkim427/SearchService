package search.blog.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.blog.cache.domain.PopularSearchQuery;

public interface PopularSearchRepository extends JpaRepository<PopularSearchQuery, Long> {
}
