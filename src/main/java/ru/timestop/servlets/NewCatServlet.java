package ru.timestop.servlets;

import org.apache.log4j.Logger;
import ru.timestop.objects.Cat;
import ru.timestop.provider.ProviderFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @since 13.08.2018
 */
@WebServlet(urlPatterns = {"/public/v1/store/category/new"})
public class NewCatServlet extends HttpServlet {

    private static final long serialVersionUID = -437092293987749771L;

    private static final Logger LOG = Logger.getLogger(ru.timestop.servlets.StoreServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String newCatName = req.getParameter("category_name");
        Cat cat = new Cat();
        cat.setName(newCatName);
        ProviderFactory.getCatProvider().insertCat(cat);
        this.getServletContext().getRequestDispatcher("/newcategory.jsp").forward(req, resp);
    }
}
