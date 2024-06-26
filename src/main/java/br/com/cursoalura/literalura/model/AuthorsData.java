package br.com.cursoalura.literalura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public  record AuthorsData(
        List<AuthorData> authors
) {}

