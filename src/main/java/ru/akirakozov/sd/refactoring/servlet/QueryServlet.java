package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.html.ProductPrinter;
import ru.akirakozov.sd.refactoring.models.Product;

import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        ProductPrinter productPrinter = new ProductPrinter(printWriter);

        if ("max".equals(command)) {
            Optional<Product> maxPriceProduct = productDAO.findMaxPrice();
            productPrinter.printProduct(maxPriceProduct, "Product with max price: ");
        } else if ("min".equals(command)) {
            Optional<Product> minPriceProduct = productDAO.findMinPrice();
            productPrinter.printProduct(minPriceProduct, "Product with min price: ");
        } else if ("sum".equals(command)) {
            long sum = productDAO.getSum();
            productPrinter.print(sum, "Summary price: ");
        } else if ("count".equals(command)) {
            int count = productDAO.getCount();
            productPrinter.print(count, "Number of products: ");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

}