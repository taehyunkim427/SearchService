package search.blog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import search.blog.api.entity.Search;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("SELECT s.query, COUNT(s) FROM Search s GROUP BY s.query ORDER BY COUNT(s) DESC")
    List<Object[]> getTop10QueriesWithCount();
}
