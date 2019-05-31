package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rw.entity.Ticket;
import rw.entity.User;
import rw.services.TrainService;
import rw.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chebotar_do on 31.05.2019.
 */

@Controller
public class ReturnTicketController {

    @Autowired
    TrainService trainService;

    @Autowired
    UserService userService;


    @GetMapping("/returnticket")
    public String returnTicket(Model model, Principal user){
        User usr = userService.getUserByLogin(user.getName());
        List<Ticket> tickets = new ArrayList<Ticket>(trainService.getTicketsByUser(usr));
        if (tickets.isEmpty()){
            model.addAttribute("errormsg", "No tickets");
        }
        model.addAttribute("tickets", tickets);

        return "returnticket";
    }

    @GetMapping("/return/{id}")
    public String rTicket(@PathVariable(value = "id") long id){
        trainService.returnTicketById(id);
        return "redirect:/returnticket";
    }
}
