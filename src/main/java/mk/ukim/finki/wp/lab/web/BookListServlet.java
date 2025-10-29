package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookListServlet",urlPatterns = "/*")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    public final BookService bookService;
    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("books",bookService.listAll());
        context.setVariable("ipAddress", req.getRemoteAddr());
        List<Book> searchedBooks = (List<Book>) req.getSession().getAttribute("searchedBooks");
        req.getSession().removeAttribute("searchedBooks");
        context.setVariable("searchedBooks",searchedBooks);
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText=req.getParameter("searchText");
        String ratingValue=req.getParameter("ratingValue");

        try {
            double number = Double.parseDouble(ratingValue);
            List<Book> searchedBooks=bookService.searchBooks(searchText,number);
            req.getSession().setAttribute("searchedBooks",searchedBooks);
            System.out.println("Успешно парсирана бројка: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Неуспешно парсирање! Вредноста не е број.");
        }
        resp.sendRedirect("/");
    }
}
