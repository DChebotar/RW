package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rw.entity.CarrageType;
import rw.services.CarrageService;
import rw.services.TrainService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Controller
public class AdminController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private CarrageService carrageService;

    @GetMapping("/admin")
    public String admin(Model model){
        List<CarrageType> types = Arrays.asList(CarrageType.values());
        model.addAttribute("types", types);
        return "admin";
    }

    @PostMapping("/admin/createtrain")
    public String createTrain(@RequestParam String arrStation,
                              @RequestParam String depStation,
                              @RequestParam String arrTime,
                              @RequestParam String depTime,
                              @RequestParam Map<String, String> numberOfCarrages,
                              Model model
                              ){
        Timestamp arTime = new Timestamp(System.currentTimeMillis());
        if (!arrTime.isEmpty()) {
            arTime = Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(arrTime.substring(0, 4)), Integer.parseInt(arrTime.substring(5, 7)),
                    Integer.parseInt(arrTime.substring(8, 10)), Integer.parseInt(arrTime.substring(11, 13)), Integer.parseInt(arrTime.substring(14,16))));
        }
        else {
            arTime.setTime(0);
        }
        Timestamp dpTime = new Timestamp(System.currentTimeMillis());
        if (!depTime.isEmpty()) {
            dpTime = Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(depTime.substring(0, 4)), Integer.parseInt(depTime.substring(5, 7)),
                    Integer.parseInt(depTime.substring(8, 10)), Integer.parseInt(depTime.substring(11, 13)), Integer.parseInt(depTime.substring(14,16))));
        }
        else {
            dpTime.setTime(0);
        }
        trainService.createTrain(arrStation, depStation, numberOfCarrages, arTime, dpTime);
        return "redirect:/main";}
}
