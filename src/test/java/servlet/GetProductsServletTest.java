package servlet;

import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProductsServletTest extends BaseServletTest {
    private void checkResponse(HttpResponse<String> response, List<Product> productList) {
        StringBuilder s = new StringBuilder();
        s.append("<html><body>\n");
        for (Product product : productList) {
            s.append(product.name).append("\t").append(product.price).append("</br>\n");
        }
        s.append("</body></html>\n");
        assertEquals(s.toString(), response.body());
    }

    @Test
    public void onEmpty() throws Exception {
        HttpResponse<String> response = request("/get-products");
        checkResponse(response, new ArrayList<>());
    }

    @Test
    public void simple() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("phone", 100));
        products.add(new Product("phone2", 1000));
        products.add(new Product("plane", 9999999));

        for (Product product : products) {
            assertEquals("OK\n", addProduct(product).body());
        }

        HttpResponse<String> response = request("/get-products");
        checkResponse(response, products);
    }
}