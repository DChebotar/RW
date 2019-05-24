package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rw.services.UserService;

import java.time.LocalDate;

/**
 * Created by Chebotar_do on 24.05.2019.
 */

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String regPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               @RequestParam String name,
                               @RequestParam String patronymic,
                               @RequestParam String surname,
                               @RequestParam String passportSeries,
                               @RequestParam String passportNumber,
                               @RequestParam String passportIssueDate,
                               @RequestParam String passportIssueBy,
                               @RequestParam String email,
                               Model model){
        LocalDate localDatePassportIssueDate = LocalDate.MIN;
        if (!passportIssueDate.isEmpty()) {
            localDatePassportIssueDate = LocalDate.of(Integer.parseInt(passportIssueDate.substring(0, 4)),
                    Integer.parseInt(passportIssueDate.substring(5, 7)), Integer.parseInt(passportIssueDate.substring(8)));
        }

        if (!StringUtils.isEmpty(password) && !password.equals(confirmPassword) && !StringUtils.isEmpty(confirmPassword)){
            model.addAttribute("error", "Passwords are different");
            model.addAttribute("login", login);
            model.addAttribute("name", name);
            model.addAttribute("patronymic", patronymic);
            model.addAttribute("surname", surname);
            model.addAttribute("passportSeries", passportSeries);
            model.addAttribute("passportNumber", passportNumber);
            model.addAttribute("passportIssueDate", passportIssueDate);
            model.addAttribute("passportIssueBy", passportIssueBy);
            model.addAttribute("email", email);
            return "registration";
        }

        if (!userService.createUser(login, password, name, patronymic, surname, passportSeries, passportNumber, localDatePassportIssueDate, passportIssueBy, email)){
            model.addAttribute("error", "User exists!");
            model.addAttribute("name", name);
            model.addAttribute("patronymic", patronymic);
            model.addAttribute("surname", surname);
            model.addAttribute("passportSeries", passportSeries);
            model.addAttribute("passportNumber", passportNumber);
            model.addAttribute("passportIssueDate", passportIssueDate);
            model.addAttribute("passportIssueBy", passportIssueBy);
            model.addAttribute("email", email);
            return "registration";
        }

        return "login";
    }
}
