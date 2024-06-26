package br.com.cursoalura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Entity
@Table(name="books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors;
    private List<String> languages;
    private Integer downloadCount;

    public Books(){}

    public Books(String title, List<Authors> authors, List<String> languages, Integer downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = OptionalInt.of(downloadCount).orElse(0);

    }

    public Books(Books books) {
        this.title = books.getTitle();
        this.authors = books.getAuthors();
        this.languages = books.getLanguages();
        this.downloadCount = OptionalInt.of(books.getDownloadCount()).orElse(0);

    }

    public Books(String bookName, List<Authors> authors) {
        this.title = bookName;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List <Authors> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Título='" + title + "\n" +
                ", Autores da Obra=" + authors.stream()
                .map(Authors::getName)
                .collect(Collectors.joining(", "))+"\n"+
                ", Línguas Disponíveis=" + languages + "\n"+
                ", Número de Downloads=" + downloadCount + "\n";
    }
}
