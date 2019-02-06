package main.java.hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) 
    {
        Greeting greeting = new Greeting();
        greeting.setName(name);
        greeting.setMessage(String.format(template, name));
        return greeting;
    }
}