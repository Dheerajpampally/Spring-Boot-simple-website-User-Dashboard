package demo.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {

    @GetMapping("/")
    public String front() {
        return "Front"; // Return the name of HTML file without the ".html" extension
    }

    @GetMapping("/index")
    public String index1() {
        return "index"; // Return the name of your HTML file without the ".html" extension
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile"; // Return the name of your HTML file without the ".html" extension
    }

}