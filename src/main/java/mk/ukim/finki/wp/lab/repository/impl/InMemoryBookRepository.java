package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryBookRepository  {
    private final DataHolder dataHolder;
    private final BookRepository bookRepository;

    public InMemoryBookRepository(DataHolder dataHolder, BookRepository bookRepository) {
        this.dataHolder = dataHolder;
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooksByTitleContainingIgnoreCaseAndAverageRatingGreaterThan(text, rating);
    }

    public void createBook(String title, String genre, double averageRating, Author author) {
        bookRepository.save(new Book(title, genre, averageRating, author));
    }


    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
