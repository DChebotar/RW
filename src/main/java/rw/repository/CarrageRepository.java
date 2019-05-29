package rw.repository;

import rw.entity.AbstractCarrage;
import rw.entity.PassangerCarrage;
import rw.entity.Seat;
import rw.entity.Train;

import java.util.List;

/**
 * Created by Chebotar_do on 28.05.2019.
 */
public interface CarrageRepository {

    void saveCarrage(AbstractCarrage carrage);

    void saveSeat(Seat seat);

    List<PassangerCarrage> getCarragesByTrain(Train train);
}
