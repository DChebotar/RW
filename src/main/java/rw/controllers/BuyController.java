package rw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.entity.*;
import rw.services.CarrageService;
import rw.services.TrainService;
import rw.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chebotar_do on 31.05.2019.
 */

@Controller
public class BuyController {

    @Autowired
    TrainService trainService;

    @Autowired
    CarrageService carrageService;

    @Autowired
    UserService userService;

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable(value = "id") Long id, Principal user, Model model) {
        Train train = trainService.getTrainById(id);
        model.addAttribute("train", train);
        List<PassangerCarrage> carrages = carrageService.getCarragesByTrain(train);
        model.addAttribute("carrages", carrages);

        return "buy";
    }

    @PostMapping("/buy/getTicket")
    public String buyTicket(@RequestParam long id,
                            @RequestParam int numberOfCarrage,
                            @RequestParam String numberOfSeat,
                            Principal user) {
        User usr = userService.getUserByLogin(user.getName());
        trainService.buyTicket(id, numberOfCarrage, numberOfSeat, usr);

        return "redirect:/main";
    }

    @PostMapping("/buy/getseats")
    @ResponseBody
    public List<String> getSeats(@RequestParam long trainid,
                                 @RequestParam int carrageNumber) {
        List<String> listSeats = new ArrayList<String>();
        PassangerCarrage carrage = carrageService.getCarrageByTrainAndCarrageNumber(trainService.getTrainById(trainid), carrageNumber);
        List<Seat> seats = carrageService.getSeats(carrage);
        for (Seat seat : seats) {
            if (seat.isStatus() == true) {
                listSeats.add(seat.getNumber());
            }
        }
        return listSeats;
    }

    @PostMapping("/buy/getcarragenumber")
    @ResponseBody
    public List<Integer> getNumbersCarrages(@RequestParam long trainid,
                                            @RequestParam String typeCar) {
        List<Integer> numbers = new ArrayList<Integer>();
        List<PassangerCarrage> carrages = carrageService.getCarragesByTrain(trainService.getTrainById(trainid));
        for (PassangerCarrage carrage : carrages) {
            if (carrage.getCarrageType().equals(CarrageType.valueOf(typeCar))) {
                numbers.add(carrage.getCarrageNumber());
            }
        }
        return numbers;
    }
}
