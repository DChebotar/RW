package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rw.entity.CarrageType;
import rw.entity.Train;
import rw.services.CarrageService;
import rw.services.TrainService;
import rw.services.TrainServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Chebotar_do on 21.05.2019.
 */

@Controller
public class MainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private CarrageService carrageService;

    @GetMapping("/")
    public String start(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(Principal  user, Model model){
        model.addAttribute("user", user);
        List<Train> trains = trainService.getAllTrains();
        if (trains.isEmpty()){
            model.addAttribute("errormsg", "Trains not found");
        }


        model.addAttribute("trains", trains);


        model.addAttribute("types", CarrageType.values());
        return "main";


    }
}


