package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import rw.entity.CarrageType;
import rw.entity.Train;
import rw.services.CarrageService;
import rw.services.TrainService;
import rw.services.TrainServiceImpl;

import java.security.Principal;
import java.util.*;

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

        return "main";
    }

    @PostMapping("/search")
    public String search(@RequestParam String arrStation,
                         @RequestParam String depStation,
                         Principal  user,
                         Model model){
        model.addAttribute("user", user);
        List<Train> trains = trainService.getAllTrains();
        List<Train> filterTrains = new ArrayList<Train>();
        if (trains.isEmpty()){
            model.addAttribute("errormsg", "Trains not found");
        }
        if (StringUtils.isEmpty(arrStation) && StringUtils.isEmpty(depStation)){
            model.addAttribute("trains", trains);
            return "main";
        }
        for (Train train : trains){
            if (!StringUtils.isEmpty(arrStation) && !StringUtils.isEmpty(depStation) &&
                    train.getRoute().getArrivalStation().equals(arrStation) && train.getRoute().getDepartureStation().equals(depStation)){
               filterTrains.add(train);
            }
            if (!StringUtils.isEmpty(arrStation) && StringUtils.isEmpty(depStation) &&
                    train.getRoute().getArrivalStation().equals(arrStation)){
                filterTrains.add(train);
            }
            if (StringUtils.isEmpty(arrStation) && !StringUtils.isEmpty(depStation) &&
                    train.getRoute().getDepartureStation().equals(depStation)){
                filterTrains.add(train);
            }
        }
        if (filterTrains.isEmpty()){
            model.addAttribute("errormsg", "Trains not found");
        }
        model.addAttribute("trains", filterTrains);

        return "main";
    }

    @PostMapping("/main/types")
    @ResponseBody
    public Set<String> getTypesOfCarrages(@RequestParam String trainid){
        Set<CarrageType> carrageTypes = trainService.getTypesOfCarrages(trainService.getTrainById(Long.parseLong(trainid)));
        Set<String> ct = new HashSet<String>();
        for (CarrageType carrageType : carrageTypes){
            ct.add(carrageType.name().toString());
        }
        return ct;
    }

    @PostMapping("/main/freetickets")
    @ResponseBody
    public List<String> getFreeTickets(@RequestParam String trainid){
        Set<String> carrageTypes = getTypesOfCarrages(trainid);
        Map<CarrageType, Integer> fTickets = trainService.getFreeTickets(trainService.getTrainById(Long.parseLong(trainid)));
        List<String> freeTickets = new ArrayList<String>();

        for (String carrageType : carrageTypes){
            freeTickets.add(String.valueOf(fTickets.get(CarrageType.valueOf(carrageType))));
        }

        return freeTickets;
    }
}


