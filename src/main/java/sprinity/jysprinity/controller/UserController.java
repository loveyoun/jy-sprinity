package sprinity.jysprinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sprinity.jysprinity.domain.User;
import sprinity.jysprinity.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired //JPA api를 이용하면 내가 인스턴스를 만들 필요가 없음. spring boot가 만들어줘서 어딘가 있다
    private UserRepository userRepository;
    //private List<User> users = new ArrayList<User>(); list collection

    /*
     * 로 그 인
     * */
    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if (user == null) return "redirect:/users/login";
        if (!user.matchPassword(password)) return "redirect:/users/login"; //!password.equals(user.getPassword())

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user); //model data key랑 달라야함. "sessionUser"

        return "redirect:/";
    }

    /*
     * 로 그 아 웃
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY); //"sessionUser"
        return "redirect:/";
    }

    /*
     * 회 원 가 입
     * */
    @GetMapping("/form")    //static.user.form.html이 아니라, template.user.form.html 접근 위해서
    public String form() {
        return "/user/form";
    }

    /*서버로 데이터 받은 것*/
    @PostMapping("")    //RequestMapping 때문에. @PostMapping("/user/create")
    //@GetMapping("/create"). 보안 issue
    public String create_u(User user) {
        //public String create(String userId, String password, String name, String email)
        //System.out.println("user: " + user); toString()으로 가능
        //users.add(user);
        userRepository.save(user);  //jpa

        //return "index"; //template.list.html로 감
        //return "redirect:/user/list"; //URL @GetMapping("/user/list")로 감. 두 페이지 간 연결
        return "redirect:/users";
    }

    @GetMapping("") //Post랑 Get이니까 "/users" 같아도 됨
    //@GetMapping("/user/list") 회원목록 받아오는 거니까 get. localhost:8080/user/list 로 url을 받으면,
    //list 이름 다 같아도 되네???
    public String list_u(Model model) {  //html로 보내기 위해서 model이 필요하다
        //model.addAttribute("users", users);
        model.addAttribute("users", userRepository.findAll());

        return "/user/list";
    }

    /*
     **회원정보 수정**
     */
    @GetMapping("/{id}/form")  //url에서 id값 얻어와야 한다. @PathVariable 과 같은 이름
    //이럴 떄, html의 css의 경로를 절대경로로 바꾸어 주어야 한다
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        /* plus) 로그인 해야지 수정버튼 누를 수 있게
         *Object tempUser = session.getAttribute("sessionUser");
         *if (tempUser == null) return "redirect:/users/login";
         *!!보안 plus) 로그인하면 다른 사용자 /users/{{id}}/form 으로 들어갈 수 있다
         *User sessionUser = (User) tempUser;*/
        if (!HttpSessionUtils.isLoginUser(session)) return "redirect:/users/login";

        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        if (!sessionUser.matchId(id))
            throw new IllegalStateException("자신의 정보만 수정할 수 있습니다"); //!id.equals(sessionUser.getId())

        //User user = userRepository.findOne(id);
        User user = userRepository.findById(id).get(); //Optional //OR id -> sessionUser.getId()
        model.addAttribute("user", user);

        return "/user/updateForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) return "redirect:/users/login";

        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        if (!sessionUser.matchId(id)) throw new IllegalStateException("자신의 정보만 수정할 수 있습니다");

        User user = userRepository.findById(id).get();
        user.update(updatedUser);
        //db 저장
        userRepository.save(user); //id가 없으면 insert, 있으면 update
        //session 저장
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

        return "redirect:/users";
    }

}
