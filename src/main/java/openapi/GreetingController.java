package openapi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "greeting.json", method = RequestMethod.GET)
    public @ResponseBody Greeting greetingJSON(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "greeting.htm", method = RequestMethod.GET)
    public String greetingHTM(Model model, @RequestParam(value="name", required=false, defaultValue="Dood!") String name) {
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", name);
        model.addAllAttributes(attributesMap);
        return "greeting";
    }
}