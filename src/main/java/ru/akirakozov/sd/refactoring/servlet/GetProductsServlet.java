package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.html.ProductPrinter;

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
        ProductPrinter productPrinter = new ProductPrinter(response.getWriter());
        productPrinter.printProductsList(productDAO.getAll());
    }
}