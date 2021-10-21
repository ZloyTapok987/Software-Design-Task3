package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.models.Product;

import java.util.List;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends ProductServlet {

    public GetProductsServlet(ProductDAO productDAO) {
        super(productDAO);
    }

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter printerWriter = response.getWriter();
        List<Product> products = productDAO.getAll();
        printerWriter.println("<html><body>");
        for (Product product : products) {
            printerWriter.println(product.toHTML());
        }
        printerWriter.println("</body></html>");
    }
}
