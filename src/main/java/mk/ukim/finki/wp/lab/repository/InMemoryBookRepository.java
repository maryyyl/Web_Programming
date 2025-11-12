package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final DataHolder dataHolder;

    public InMemoryBookRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(b-> b.getTitle().contains(text) && b.getAverageRating()>=rating).toList();
    }

    @Override
    public void createBook(String title, String genre, double averageRating, Author author) {
        DataHolder.books.add (new Book(title,genre,averageRating,author));
    }


    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst().get();
    }
    @Override
    public void remove(Long id) {
        DataHolder.books.removeIf(b-> b.getId().equals(id));
    }
}
