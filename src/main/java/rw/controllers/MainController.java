package rw.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import rw.entity.User;

import java.security.Principal;

/**
 * Created by Chebotar_do on 21.05.2019.
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String start(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(Principal  user, Model model){
        model.addAttribute("user", user);
        return "main";
    }
}
