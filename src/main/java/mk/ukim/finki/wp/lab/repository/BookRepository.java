package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaSpecificationRepository<Book,Long> {
    List<Book> searchBooksByTitleContainingIgnoreCaseAndAverageRatingGreaterThan(String text, Double rating);
}
