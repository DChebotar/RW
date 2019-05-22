package rw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Chebotar_do on 21.05.2019.
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String start(){
        return "redirect:/main";
    }
}
