package kr.co.chunjae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginfailed")
    public String loginerror(Model model) {
        model.addAttribute("error", "true");  // 모델 속성 error에 true 값을 저장
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }
}
