package ru.akirakozov.sd.refactoring.servlet;

import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.models.Product;

/**
 * @author akirakozov
 */
public class QueryServlet extends ProductServlet {

    public QueryServlet(ProductDAO productDAO) {
        super(productDAO);
    }

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String command = request.getParameter("command");
        PrintWriter printWriter = response.getWriter();

        if ("max".equals(command)) {
            Optional<Product> maxPriceProduct = productDAO.findMaxPrice();
            printWriter.println("<html><body>");
            printWriter.println("<h1>Product with max price: </h1>");
            maxPriceProduct.ifPresent(p -> printWriter.println(p.toHTML()));
            printWriter.println("</body></html>");
        } else if ("min".equals(command)) {
            Optional<Product> minPriceProduct = productDAO.findMinPrice();
            printWriter.println("<html><body>");
            printWriter.println("<h1>Product with min price: </h1>");
            minPriceProduct.ifPresent(p -> printWriter.println(p.toHTML()));
            printWriter.println("</body></html>");
        } else if ("sum".equals(command)) {
            long summaryPrice = productDAO.getSum();
            printWriter.println("<html><body>");
            printWriter.println("Summary price: ");
            printWriter.println(summaryPrice);
            printWriter.println("</body></html>");
        } else if ("count".equals(command)) {
            int count = productDAO.getCount();
            printWriter.println("<html><body>");
            printWriter.println("Number of products: ");
            printWriter.println(count);
            printWriter.println("</body></html>");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

}