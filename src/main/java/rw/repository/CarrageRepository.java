package rw.repository;

import rw.entity.AbstractCarrage;
import rw.entity.Seat;

/**
 * Created by Chebotar_do on 28.05.2019.
 */
public interface CarrageRepository {

    void saveCarrage(AbstractCarrage carrage);

    void saveSeat(Seat seat);
}
