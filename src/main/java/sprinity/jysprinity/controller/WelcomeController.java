package sprinity.jysprinity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sprinity.jysprinity.domain.User;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @GetMapping("/helloworld")
    public String welcome(Model model){
    //public String welcome(String name, int age, Model model)
        //List Collection: List<User> repo  = Arrays.asList(new User("javajigi"), new User("sanjigi"));
        model.addAttribute("name", "javajigi");
        //model.addAttribute("name", name);
        model.addAttribute("value", 10000);
        model.addAttribute("taxed_value", 30);
        model.addAttribute("in_ca", true);

        return "welcome";
    }

}
