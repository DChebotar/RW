package rw.repository;

import rw.entity.AbstractCarrage;
import rw.entity.PassangerCarrage;
import rw.entity.Seat;
import rw.entity.Train;

import java.util.List;
import java.util.Set;

/**
 * Created by Chebotar_do on 28.05.2019.
 */
public interface CarrageRepository {

    void saveCarrage(AbstractCarrage carrage);

    void saveSeat(Seat seat);

    List<PassangerCarrage> getCarragesByTrain(Train train);

    List<Seat> getSeats(PassangerCarrage carrage);

    PassangerCarrage getCarrageByTrainAndCarrageNumber(Train train, int carrageNumber);

    Seat getSeatByCarrageAndNumber(PassangerCarrage carrage, String numberOfSeat);
}
