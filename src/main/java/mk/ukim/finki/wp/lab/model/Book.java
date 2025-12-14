package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.Genre;

@Data
@AllArgsConstructor
@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double averageRating;
    @ManyToOne // edna kniga eden avtor, moze da si nacrtas za da znaes
    private Author author;
    public Book(String title,Genre genre, double averageRating,Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }
}
