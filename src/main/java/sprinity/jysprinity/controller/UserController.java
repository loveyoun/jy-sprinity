package sprinity.jysprinity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sprinity.jysprinity.domain.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<User>();


    /*서버로 데이터 받은 것*/
    @PostMapping("/create")
    //@GetMapping("/create"). 보안 issue
    public String createm(User user){
    //public String create(String userId, String password, String name, String email)
        //System.out.println("user: " + user); toString()으로 가능
        users.add(user);
        //return "index"; //template.list.html로 감
        return "redirect:/listu"; //URL localhost:8080/list로 감. 두 페이지 간 연결.
    }

    @GetMapping("/listu") //회원목록 받아오는 거니까 get. localhost:8080/list로 url을 받으면,
    public String listm(Model model){
        model.addAttribute("users", users);
        return "listh";
    }

}
