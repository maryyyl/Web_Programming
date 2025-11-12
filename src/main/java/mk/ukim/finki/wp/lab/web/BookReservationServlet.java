package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    public final BookReservationService bookReservationService;

    public BookReservationServlet(SpringTemplateEngine springTemplateEngine, BookReservationService bookReservationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookReservationService = bookReservationService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chosenBook = req.getParameter("chosenBook");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numOfCopies=req.getParameter("numCopies");
        HashMap<String, ArrayList<Book>> bookReservationsPerUser = (HashMap<String, ArrayList<Book>>) req.getServletContext().getAttribute("map2");
        if(bookReservationsPerUser.get(readerName)==null){
            ArrayList<Book> books = new ArrayList<>();
            books.add(new Book(chosenBook,readerAddress,Integer.parseInt(numOfCopies),DataHolder.authors.getFirst()));
            bookReservationsPerUser.put(readerName,books);
        }
        else {
            ArrayList<Book> list = bookReservationsPerUser.get(readerName);
            list.add(new Book(chosenBook, readerAddress, Integer.parseInt(numOfCopies), DataHolder.authors.getLast()));
            bookReservationsPerUser.put(readerName,list);
        }
        Long numCopies=Long.parseLong(numOfCopies);
        HashMap<String,Integer> map=(HashMap<String, Integer>) req.getServletContext().getAttribute("map");
        if(!map.containsKey(chosenBook)){
            map.put(chosenBook,Integer.parseInt(numOfCopies));
        }
        else {
            map.compute(chosenBook, (k, lastCopiesNumber) -> lastCopiesNumber + Integer.parseInt(numOfCopies));
        }
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chosenBook", chosenBook);
        context.setVariable("readerName", readerName);
        context.setVariable("readerAddress", readerAddress);
        context.setVariable("numCopies", numCopies);
        context.setVariable("ipAddress", req.getRemoteAddr());

        this.bookReservationService.placeReservation(chosenBook,readerName,readerAddress,numCopies);
        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());

    }
}
