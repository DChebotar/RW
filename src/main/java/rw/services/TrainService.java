package rw.services;

import rw.entity.Train;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface TrainService {

    List<Train> getAllTrains();

    void createTrain(String arrStation, String depStation, Map<String, String> carrages, Timestamp arTime, Timestamp dpTime);
}
