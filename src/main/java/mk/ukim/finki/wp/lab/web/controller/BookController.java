package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genre;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    @PostMapping("searchBook")
    public String searchBooksPage(@RequestParam(required = false) String error, @RequestParam String searchText,
                                  @RequestParam  Double ratingValue, RedirectAttributes redirectAttributes, Model model){
        List<Book> searchedBooks = bookService.searchBooks(searchText,ratingValue);
        System.out.println(searchText);
        System.out.println(ratingValue);
        System.out.println(searchedBooks!=null?"True":"False");
        redirectAttributes.addFlashAttribute("searchedBooks", searchedBooks);
        return "redirect:/books";
    }
    @GetMapping("books/add-form")
    public String addBookForm(Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", Genre.values());
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
                           @RequestParam Genre genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.createBook(title, (genre),averageRating,authorService.findById(authorId));
        return "redirect:/books";
    }
    @PostMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam Genre genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        Book book = bookService.findById(bookId);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(authorService.findById(authorId));
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.remove(id);
        return "redirect:/books";
    }
}
