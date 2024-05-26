package tr.edu.metu.sm703;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Hello World");
    }

    @Get("/add")
    public Map<String, Object> add(@QueryValue int a, @QueryValue int b) {
        Map<String, Object> response = new HashMap<>();
        response.put("a", a);
        response.put("b", b);
        response.put("sum", a + b);
        return response;
    }

    @Get("/highRequestRate")
    public Map<String, Object> highRequestRate() {
        return Collections.singletonMap("message", "High request rate endpoint");
    }

    @Get("/slowResponse")
    public Map<String, Object> slowResponse() throws InterruptedException {
        // Simulate a slow response
        Thread.sleep(5000); // Sleep for 5 seconds
        return Collections.singletonMap("message", "Slow response endpoint");
    }

    @Get("/simulateError")
    public Map<String, Object> simulateError() {
        throw new RuntimeException("Simulated error");
    }
}
