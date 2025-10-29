package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    private void init() {
        books.add(new Book("The Nightingale", "Historical Fiction", 4.64));
        books.add(new Book("Just Mercy", "Memoir / Social Justice", 4.62));
        books.add(new Book("The Name of the Wind", "Fantasy", 4.52));
        books.add(new Book("Calvin and Hobbes", "Comic / Humor", 4.61));
        books.add(new Book("The Way of Kings", "Fantasy", 4.66));
        books.add(new Book("A Voice in the Wind", "Historical Fiction", 4.57));
        books.add(new Book("The Book Thief", "Historical Fiction", 4.37));
        books.add(new Book("Where the Crawdads Sing", "Mystery / Drama", 4.47));
        books.add(new Book("Educated", "Memoir", 4.47));
        books.add(new Book("The Silent Patient", "Psychological Thriller", 4.24));
    }
}
