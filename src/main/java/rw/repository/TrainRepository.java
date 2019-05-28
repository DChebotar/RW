package rw.repository;

import rw.entity.Train;

import java.util.List;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface TrainRepository {

    List<Train> getAllTrains();

    void saveTrain(Train train);
}
