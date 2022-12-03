package sprinity.jysprinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sprinity.jysprinity.domain.Question;
import sprinity.jysprinity.domain.User;
import sprinity.jysprinity.repository.QuestionRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired private QuestionRepository questionRepository; //db에 저장하기 위해서~

    @GetMapping("/qnaForm")
    public String qnaForm_f(HttpSession session){
        //로그인한 사용자에 대해서만 접근
        if(!HttpSessionUtils.isLoginUser(session)) return "/users/login";

        return "/qna/qnaForm";
    }

    @PostMapping("")
    public String create_q(String title, String contents, HttpSession session) {
        if(!HttpSessionUtils.isLoginUser(session)) return "/users/login";
        User sessionUser = HttpSessionUtils.getUserFromSession(session);

        Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
        questionRepository.save(newQuestion);

        return "redirect:/";
    }





}
