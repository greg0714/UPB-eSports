package main.java.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController 
{
	@Autowired GreetingDAO greetingDAO;

    @CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(String name) 
    {
        return greetingDAO.retrieveByName(name);
    }
}