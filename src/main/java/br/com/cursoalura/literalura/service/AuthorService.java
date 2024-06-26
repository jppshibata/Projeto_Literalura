package br.com.cursoalura.literalura.service;

import br.com.cursoalura.literalura.model.Authors;
import br.com.cursoalura.literalura.model.Books;
import br.com.cursoalura.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public void getListAuthors() {
        List<Authors> authorsList = authorRepository.findAllAuthors();
        authorsList.forEach(author -> {
            List<Books> books = author.getBooks();
            StringBuilder bookTitles = new StringBuilder();
            for (Books book : books) {
                bookTitles.append(book.getTitle()).append(", ");
            }

            System.out.printf(
                    """
                    Autor: %s
                    Ano de Nascimento: %s
                    Ano de Falecimento: %s
                    Livros: %s
                    %n
                    """,
                    author.getName(),
                    author.getBirthYear(),
                    author.getDeathYear(),
                    bookTitles.length() > 0 ? bookTitles.substring(0, bookTitles.length() - 2) : "Nenhum livro cadastrado"
            );
        });
    }

    public void getAuthorsIfAliveByYear() {
        System.out.println("Insira o ano para a busca");
        Integer yearLimit = scanner.nextInt();
            List<Authors> authorsList = authorRepository.findAuthorAliveByYear(yearLimit);
            authorsList.forEach(author -> {
                List<Books> books = author.getBooks();
                StringBuilder bookTitles = new StringBuilder();
                for (Books book : books) {
                    bookTitles.append(book.getTitle()).append(", ");
                }

                System.out.printf(
                        """
                        Autor: %s
                        Ano de Nascimento: %s
                        Ano de Falecimento: %s
                        Livros: %s
                        %n
                        """,
                        author.getName(),
                        author.getBirthYear(),
                        author.getDeathYear() != null ? author.getDeathYear() : "Vivo",
                        bookTitles.length() > 0 ? bookTitles.substring(0, bookTitles.length() - 2) : "Nenhum livro cadastrado"
                );
            });
        }

}