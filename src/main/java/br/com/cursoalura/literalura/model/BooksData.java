package br.com.cursoalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData( String title,
                         List<AuthorData> authors,
                         List<String> languages,
                        @JsonAlias("download_count") Integer downloadCount) {
}
