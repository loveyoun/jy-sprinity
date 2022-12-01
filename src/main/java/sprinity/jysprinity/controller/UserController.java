package sprinity.jysprinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sprinity.jysprinity.domain.User;
import sprinity.jysprinity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired //JPA api를 이용하면 내가 인스턴스를 만들 필요가 없음. spring boot가 만들어줘서 어딘가 있다
    private UserRepository userRepository;
    //private List<User> users = new ArrayList<User>(); list collection

    /*서버로 데이터 받은 것*/
    @PostMapping("")    //RequestMapping 때문에
    //@PostMapping("/user/create")
    //@GetMapping("/create"). 보안 issue
    public String create_f(User user){
    //public String create(String userId, String password, String name, String email)
        //System.out.println("user: " + user); toString()으로 가능
        //users.add(user);
        userRepository.save(user);
        //return "index"; //template.list.html로 감
        //return "redirect:/user/list"; //URL @GetMapping("/user/list")로 감. 두 페이지 간 연결.
        return "redirect:/users";
    }

    @GetMapping("") //Post랑 Get이니까 "/users" 같아도 됨
    //@GetMapping("/user/list") 회원목록 받아오는 거니까 get. localhost:8080/user/list 로 url을 받으면,
    //list 이름 다 같아도 되네???
    public String list_f(Model model){
        //model.addAttribute("users", users);
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

}
