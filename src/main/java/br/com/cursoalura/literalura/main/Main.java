package br.com.cursoalura.literalura.main;
import br.com.cursoalura.literalura.repository.AuthorRepository;
import br.com.cursoalura.literalura.repository.BookRepository;
import br.com.cursoalura.literalura.service.AuthorService;
import br.com.cursoalura.literalura.service.BookService;
import jakarta.persistence.EntityManagerFactory;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManagerFactory entityManagerFactory;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository, EntityManagerFactory entityManagerFactory) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    public void showMenu() {
        BookService bookService = new BookService(bookRepository, authorRepository, entityManagerFactory);
        AuthorService authorService = new AuthorService(authorRepository);
        var option = -1;
        while(option != 6) {
            var menu = """
            Digite uma das opções:
            1- Buscar livro por título
            2- Listar livros registrados
            3- Listar autores registrados
            4- Listar autores vivos em um determinado ano
            5- Listar livros por idioma
            6- Sair
            """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    bookService.getBookByTitle();
                    break;
                case 2:
                    bookService.getListBooks();
                    break;
                case 3:
                    authorService.getListAuthors();
                    break;
                case 4:
                    authorService.getAuthorsIfAliveByYear();
                    break;
                case 5:
                    bookService.getBooksByLanguage();
                    break;
                case 6:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção Inválida");

            }

        }
    }

}
