package rw.services;

import rw.entity.AbstractCarrage;
import rw.entity.PassangerCarrage;
import rw.entity.Seat;
import rw.entity.Train;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface CarrageService {

    List<AbstractCarrage> getCarragesByFormData(Map<String, String> numberOfCarrages, Train train);

    List<PassangerCarrage> getCarragesByTrain(Train train);

    List<Seat> getSeats(PassangerCarrage carrage);

    PassangerCarrage getCarrageByTrainAndCarrageNumber(Train train, int carrageNumber);

    Seat getSeatByCarrageAndNumber(PassangerCarrage carrage, String numberOfSeat);
}
