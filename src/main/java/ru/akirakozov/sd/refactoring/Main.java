package ru.akirakozov.sd.refactoring;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static ru.akirakozov.sd.refactoring.database.SQLiteDatabase.createTables;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        createTables();

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ProductDAO productDAO = new ProductDAO();
        context.addServlet(new ServletHolder(new AddProductServlet(productDAO)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(productDAO)),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(productDAO)),"/query");

        server.start();
        server.join();
    }
}