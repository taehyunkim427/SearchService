package search.blog.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.blog.cache.domain.PopularSearch;

public interface PopularSearchRepository extends JpaRepository<PopularSearch, Long> {
}
