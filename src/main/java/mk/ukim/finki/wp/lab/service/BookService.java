package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genre;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    void createBook(String title, Genre genre, double averageRating, Author author);
    Book findById(Long id);
    void remove(Long id);
    void updateBook(Book book);
}
