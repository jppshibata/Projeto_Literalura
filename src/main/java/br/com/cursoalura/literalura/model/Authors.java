package br.com.cursoalura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer birthYear;
    private Integer deathYear;
    private String name;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Books> books;

    public Authors(){}

    public Authors(AuthorData authorData) {
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear() != null ? authorData.deathYear() : 0;
        this.name = authorData.name();
    }

    public Authors(Authors authors) {
        this.birthYear = authors.birthYear;
        this.deathYear = authors.deathYear != null ? authors.deathYear : 0;
        this.name = authors.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
