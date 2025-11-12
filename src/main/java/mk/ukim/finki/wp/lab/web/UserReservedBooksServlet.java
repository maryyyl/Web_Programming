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
import java.util.HashMap;

@WebServlet(name = "UserReservedBooks",urlPatterns = "/bookReservation1")
public class UserReservedBooksServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    public final BookService bookService;

    public UserReservedBooksServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        String userName=req.getParameter("readerName");
        String userAddress=req.getParameter("readerAddress");
        WebContext context=new WebContext(webExchange);
        HashMap<String, ArrayList<Book>> map=(HashMap<String, ArrayList<Book>>) req.getServletContext().getAttribute("map2");
        if(map.containsKey(userName)){
            ArrayList<Book> books=map.get(userName);
            context.setVariable("books",books);
        }
        else if(!map.containsKey(userName) || map.get(userName).size()==0) {
            context.setVariable("error","The user has not reserved books yet!");
        }
        context.setVariable("readerName",userName);
        springTemplateEngine.process("userReservedBooks.html", context, resp.getWriter());
    }
}
