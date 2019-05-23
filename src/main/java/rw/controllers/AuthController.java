package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chebotar_do on 23.05.2019.
 */

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    UserDetailsService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String login(@RequestParam(name = "error", required = false) boolean error, Model model){
        if (error){
            model.addAttribute("error", true);
        }
        return "login";
    }
}
