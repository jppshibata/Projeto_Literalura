package br.com.cursoalura.literalura;

import br.com.cursoalura.literalura.main.Main;
import br.com.cursoalura.literalura.repository.AuthorRepository;
import br.com.cursoalura.literalura.repository.BookRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.cursoalura.literalura.model")
public class LiteraluraApplication implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final EntityManagerFactory entityManagerFactory;
	@Autowired
	public LiteraluraApplication(BookRepository bookRepository, AuthorRepository authorRepository, EntityManagerFactory entityManagerFactory) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.entityManagerFactory = entityManagerFactory;
	}
	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookRepository,authorRepository, entityManagerFactory);
		main.showMenu();
	}
}
