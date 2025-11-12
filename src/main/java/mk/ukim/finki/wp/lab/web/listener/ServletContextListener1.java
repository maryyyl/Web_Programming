package mk.ukim.finki.wp.lab.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.ArrayList;
import java.util.HashMap;

@WebListener
public class ServletContextListener1 implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        HashMap<String,Integer> map=new HashMap<>();
        HashMap<String, ArrayList<Book>> map2=new HashMap<>();
        sce.getServletContext().setAttribute("map",map);
        sce.getServletContext().setAttribute("map2",map2);
    }
}
