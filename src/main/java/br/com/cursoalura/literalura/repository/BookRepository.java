package br.com.cursoalura.literalura.repository;

import br.com.cursoalura.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books,Long> {

    Optional<Books> findByTitle(String bookName);

    @Query("SELECT b FROM Books b")
    List<Books> findAllBooks();

    @Query("SELECT b FROM Books b WHERE b.languages IN (:languages)")
    List<Books> findAllBooksByLanguage(List<String> languages);
}
