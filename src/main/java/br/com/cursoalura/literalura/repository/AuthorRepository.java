package br.com.cursoalura.literalura.repository;

import br.com.cursoalura.literalura.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByName(String authorName);

    @Query("SELECT a FROM Authors a LEFT JOIN FETCH a.books")
    List<Authors> findAllAuthors();

    @Query("SELECT a FROM Authors a LEFT JOIN FETCH a.books WHERE a.birthYear >= :yearLimit AND (a.deathYear IS NULL OR a.deathYear > :yearLimit)")
    List<Authors> findAuthorAliveByYear(Integer yearLimit);
}
