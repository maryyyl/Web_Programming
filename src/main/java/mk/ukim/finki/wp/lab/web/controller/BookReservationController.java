package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String ReserveBook(HttpServletRequest req, @RequestParam String chosenBook,
                              @RequestParam String readerName,
                              @RequestParam String readerAddress,
                              @RequestParam Long numCopies,
                              Model model)
    {
        bookReservationService.placeReservation(chosenBook,readerName,readerAddress,numCopies);
        model.addAttribute("book", chosenBook);
        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("ipAddress",req.getRemoteAddr());
        return "reservationConfirmation";
    }
}
