package ru.timestop.servlets;

import org.apache.log4j.Logger;
import ru.timestop.provider.ProviderFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Is main servlet, where find search data and throw it on jsp.
 *
 * @author NikolaevAS
 */
@WebServlet(urlPatterns = {"/public/v1/store"})
public class StoreServlet extends HttpServlet {

    private static final long serialVersionUID = -437092293987749771L;

    private static final Logger LOG = Logger.getLogger(ru.timestop.servlets.StoreServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (LOG.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder(100);
            sb.append("category_name=" + req.getParameter("category_name"));
            sb.append("; product_name=" + req.getParameter("product_name"));
            sb.append("; min_price=" + req.getParameter("min_price"));
            sb.append("; max_price=" + req.getParameter("max_price"));

            LOG.debug(sb.toString());
        }

        String cat_name = req.getParameter("category_name");
        String prod_name = req.getParameter("product_name");
        Double min_price = getPrice(req, "min_price", Double.valueOf(0.00f));
        Double max_price = getPrice(req, "max_price", Double.MAX_VALUE);

        List<?> prods = ProviderFactory.getProdProvider().find(cat_name, prod_name, min_price, max_price);

        req.setAttribute("category_name", req.getParameter("category_name"));
        req.setAttribute("product_name", req.getParameter("product_name"));
        req.setAttribute("min_price", req.getParameter("min_price"));
        req.setAttribute("max_price", req.getParameter("max_price"));
        req.setAttribute("max_price", req.getParameter("max_price"));
        req.setAttribute("prodList", prods);
        this.getServletContext().getRequestDispatcher("/store.jsp").forward(req, resp);
    }

    /**
     * @param req
     * @param name
     * @param default_value
     * @return value of req parameter or default value if not found
     */
    private static Double getPrice(HttpServletRequest req, String name, Double default_value) {
        try {
            return Double.valueOf(req.getParameter(name));
        } catch (Exception e) {
            return default_value;
        }
    }
}
