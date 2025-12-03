package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class InMemoryAuthorRepository  {
    public List<Author> findAll() {
        return DataHolder.authors;
    }


    public Author findById(long id) {
        return DataHolder.authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }
}
