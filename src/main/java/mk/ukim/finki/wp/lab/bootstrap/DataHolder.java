package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    private void init() {
        Author a1 = new Author("J.K.", "Rowling", "UK", "Fantasy writer");
        Author a2 = new Author("George", "Orwell", "UK", "Famous for 1984");
        Author a3 = new Author("Agatha", "Christie", "UK", "Queen of Mystery");
        Author a4 = new Author("Leo", "Tolstoy", "Russia", "War and Peace author");
        Author a5 = new Author("Mark", "Twain", "USA", "Adventures of Huckleberry Finn");
        if(authorRepository.findAll().isEmpty()) {
            authorRepository.saveAll(List.of(a1,a2,a3,a4,a5));
            authors=authorRepository.findAll();
        }
        if(bookRepository.findAll().isEmpty()) {
            books.add(new Book("The Nightingale", "Historical Fiction", 4.64, authors.getFirst()));
            books.add(new Book("Just Mercy", "Memoir / Social Justice", 4.62,authors.get(1)));
            books.add(new Book("The Name of the Wind", "Fantasy", 4.52,authors.get(2)));
            books.add(new Book("Calvin and Hobbes", "Comic / Humor", 4.61,authors.get(2)));
            books.add(new Book("The Way of Kings", "Fantasy", 4.66,authors.getLast()));
            books.add(new Book("A Voice in the Wind", "Historical Fiction", 4.57,authors.getLast()));
            books.add(new Book("The Book Thief", "Historical Fiction", 4.37,authors.getFirst()));
            books.add(new Book("Where the Crawdads Sing", "Mystery / Drama", 4.47,authors.get(1)));
            books.add(new Book("Educated", "Memoir", 4.47,authors.get(2)));
            books.add(new Book("The Silent Patient", "Psychological Thriller", 4.24,authors.getLast()));
            bookRepository.saveAll(books);
        }
    }
}
