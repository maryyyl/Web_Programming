package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class InMemoryAuthorRepository implements AuthorRepository {
    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Author findById(long id) {
        return DataHolder.authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }
}
