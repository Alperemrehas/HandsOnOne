package tr.edu.metu.sm703;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HomeControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testIndex() {
        Map response = client.toBlocking().retrieve("/index", Map.class);
        assertEquals("Hello World", response.get("message"));
    }

    @Test
    void testAdd() {
        Map response = client.toBlocking().retrieve("/add?a=1&b=2", Map.class);
        assertEquals(1, response.get("a"));
        assertEquals(2, response.get("b"));
        assertEquals(3, response.get("sum"));
    }
}
