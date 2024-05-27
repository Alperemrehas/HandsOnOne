package tr.edu.metu.sm703;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HomeControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    static void stopServer() {
        if (client != null) {
            client.close();
        }
        if (server != null) {
            server.stop();
        }
    }

    @Test
    void testIndex() {
        HttpRequest<Object> request = HttpRequest.GET("/");
        HttpResponse<Map> response = client.toBlocking().exchange(request, Map.class);

        assertEquals(200, response.code());
        assertNotNull(response.body());
        assertEquals("Hello World", response.body().get("message"));
    }

    @Test
    void testAdd() {
        HttpRequest<Object> request = HttpRequest.GET("/add?a=5&b=3");
        HttpResponse<Map> response = client.toBlocking().exchange(request, Map.class);

        assertEquals(200, response.code());
        assertNotNull(response.body());
        assertEquals(5, response.body().get("a"));
        assertEquals(3, response.body().get("b"));
        assertEquals(8, response.body().get("sum"));
    }
}
