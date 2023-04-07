package search.blog.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import search.blog.api.entity.Search;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Rollback(false)
class SearchRepositoryTest {

    @Autowired
    SearchRepository searchRepository;

    @Test
    public void saveAndFindSearch() {
        // Given
        Search search = Search.builder()
                .query("카카오뱅크")
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();

        // When
        Search savedSearch = searchRepository.save(search);
        Optional<Search> findSearch = searchRepository.findById(savedSearch.getId());

        // Then
        assertThat(findSearch.isPresent()).isTrue();
        assertThat(findSearch.get().getQuery()).isEqualTo(search.getQuery());
        assertThat(findSearch.get().getSort()).isEqualTo(search.getSort());
        assertThat(findSearch.get().getPage()).isEqualTo(search.getPage());
        assertThat(findSearch.get().getSize()).isEqualTo(search.getSize());
    }

}