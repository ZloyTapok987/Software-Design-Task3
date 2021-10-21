package servlet;

import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryServletTest extends BaseServletTest {
    private void checkResponse(HttpResponse<String> response, String expectedBody) {
        assertEquals("<html><body>\n" +
                expectedBody +
                "</body></html>\n", response.body());
    }

    @Test
    public void testSum() throws Exception {
        HttpResponse<String> response = sendQuery("sum");
        checkResponse(response, "Summary price: \n0\n");
    }

    @Test
    public void testUnknown() throws Exception {
        HttpResponse<String> response = sendQuery("abcdef");
        assertEquals("Unknown command: abcdef\n", response.body());
    }

    @Test
    public void testMax() throws Exception {
        assertEquals("OK\n", addProduct(new Product("phone", 1000)).body());
        assertEquals("OK\n", addProduct(new Product("trash", 0)).body());
        HttpResponse<String> response = sendQuery("max");
        checkResponse(response, "<h1>Product with max price: </h1>\nphone\t1000</br>\n");
    }

    @Test
    public void testMin() throws Exception {
        assertEquals("OK\n", addProduct(new Product("phone", 1000)).body());
        assertEquals("OK\n", addProduct(new Product("trash", 0)).body());
        HttpResponse<String> response = sendQuery("min");
        checkResponse(response, "<h1>Product with min price: </h1>\ntrash\t0</br>\n");
    }

    @Test
    public void testCount() throws Exception {
        HttpResponse<String> response = sendQuery("count");
        checkResponse(response, "Number of products: \n0\n");
    }
}
