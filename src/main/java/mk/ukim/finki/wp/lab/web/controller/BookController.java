package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @GetMapping("/books")
    public String getBooksPage
            (@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", "No books found!");
        }
        else{
           model.addAttribute("books",bookService.listAll());
        }
        return "listBooks";
    }
    @GetMapping("books/add-form")
    public String addBookForm(Model model){
        model.addAttribute("authors", authorService.findAll());
        return ("book-form");
    }
    @GetMapping("books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", bookService.findById(id));
        return "book-form";
    }
    @PostMapping("books/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.createBook(title,genre,averageRating,authorService.findById(authorId));
        return "redirect:/books";
    }
    @PostMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        Book book = bookService.findById(bookId);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(authorService.findById(authorId));
        return "redirect:/books";
    }
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.remove(id);
        return "redirect:/books";
    }
}
