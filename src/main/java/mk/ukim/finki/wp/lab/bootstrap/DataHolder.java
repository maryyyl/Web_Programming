package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    private void init() {
        authors.add(new Author("Gabriel", "García Márquez", "Colombia",
                "Nobel Prize-winning author known for magical realism in 'One Hundred Years of Solitude'."));
        authors.add(new Author("Jane", "Austen", "United Kingdom",
                "English novelist famous for her critique of social norms in 'Pride and Prejudice'."));
        authors.add(new Author("Haruki", "Murakami", "Japan",
                "Contemporary Japanese author blending realism and fantasy in works like 'Kafka on the Shore'."));
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

    }
}
