package sprinity.jysprinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sprinity.jysprinity.repository.QuestionRepository;

@Controller
public class HomeController {

    @Autowired private QuestionRepository questionRepository;
    //질문 목록 데이터 db에서 뽑아서, model에 담아서, html(template engine)에 보내주기
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("questions", questionRepository.findAll());

        return "index";
    }

}
