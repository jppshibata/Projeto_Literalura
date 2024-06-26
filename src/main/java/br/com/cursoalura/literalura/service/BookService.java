package br.com.cursoalura.literalura.service;
import br.com.cursoalura.literalura.model.*;
import br.com.cursoalura.literalura.repository.AuthorRepository;
import br.com.cursoalura.literalura.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private Scanner scanner = new Scanner(System.in);
    private ConsumeApi consume = new ConsumeApi();
    private ConvertData convert = new ConvertData();
    private static final String ADDRESS = "https://gutendex.com/books/?";
    private List<Authors> authors;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @PersistenceContext
    private final EntityManagerFactory entityManagerFactory;


    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, EntityManagerFactory entityManagerFactory) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public void getBookByTitle() {
        List<Books> bData = getBookData();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            if (!bData.isEmpty()) {
                Map<String, Authors> authorMap = new HashMap<>();
                Map<String, Books> bookMap = new HashMap<>();

                for (Books book : bData) {
                    for (Authors authorData : book.getAuthors()) {
                        String authorName = authorData.getName();
                        if (!authorMap.containsKey(authorName)) {
                            Optional<Authors> existingAuthor = authorRepository.findByName(authorName);
                            if (existingAuthor.isPresent()) {
                                authorMap.put(authorName, existingAuthor.get());
                            } else {
                                Authors author = new Authors(authorData);
                                entityManager.persist(author);
                                authorMap.put(authorName, author);
                            }
                        }
                    }

                    String bookTitle = book.getTitle();
                    if (!bookMap.containsKey(bookTitle)) {
                        Optional<Books> existingBook = bookRepository.findByTitle(bookTitle);
                        if (existingBook.isPresent()) {
                            bookMap.put(bookTitle, existingBook.get());
                        } else {
                            List<Authors> bookAuthors = new ArrayList<>(authorMap.values());
                            Books newBook = new Books(book);
                            newBook.setAuthors(bookAuthors);
                            entityManager.persist(newBook);
                            bookMap.put(bookTitle, newBook);
                        }
                    }
                }
                entityManager.getTransaction().commit();
                System.out.println("Transação finalizada");
            } else {
                System.out.println("Não há livros a serem processados.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante o processamento: " + e.getMessage());
            throw e;
        }
    }

    public List<Books> getBookData() {
        System.out.println("Digite o titulo do livro");
        var bookName = scanner.nextLine();
        var json = consume.getData(ADDRESS + "search=" + bookName.replace(" ","%20"));
        SearchData searchData = convert.getData(json, SearchData.class);
        List<Books> bookData = searchData.results().stream()
                .map(result -> {
                    List<AuthorData> authorData = result.authors();
                    authors = authorData.stream()
                            .map(data -> new Authors(data))
                            .collect(Collectors.toList());
                    return new Books(
                            result.title(),
                            authors,
                            result.languages(),
                            result.downloadCount()
                    );
                })
                .collect(Collectors.toList());
        return bookData;

    }

    public void getListBooks() {
        List<Books> booksList = bookRepository.findAllBooks();
        getList(booksList);
    }

    public void getList (List<Books> booksList){
        booksList.forEach(b -> {
            String authors = String.join(", ", b.getAuthors().stream()
                    .map(Authors::getName)
                    .collect(Collectors.toList()));
            System.out.printf(
                    """
                    --------LIVRO---------
                    Título: %s
                    Autor(es): %s
                    Idioma: %s
                    Número de Downloads: %s
                    -----------------------
                    %n
                    """,
                    b.getTitle(),
                    authors,
                    b.getLanguages(),
                    b.getDownloadCount()
            );
        });
    }

    public void getBooksByLanguage() {
        var menu = """
               Digite o idioma a ser buscado:
               1- Português Brasileiro
               2- Inglês
               3- Francês
               4- Espanhol
              """;
        System.out.println(menu);
        int num = scanner.nextInt();
        scanner.nextLine();
        List<String> languages = new ArrayList<>();
        switch (num) {
            case 1:
                languages.add("pt");
                break;
            case 2:
                languages.add("en");
                break;
            case 3:
                languages.add("fr");
                break;
            case 4:
                languages.add("es");
                break;
        }
        List<Books> booksList = bookRepository.findAllBooksByLanguage(languages);
        getList(booksList);
    }

}