package rw.services;

import rw.entity.CarrageType;
import rw.entity.Ticket;
import rw.entity.Train;
import rw.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface TrainService {

    List<Train> getAllTrains();

    void createTrain(String arrStation, String depStation, Map<String, String> carrages, Timestamp arTime, Timestamp dpTime);

    Map<CarrageType, Integer> getFreeTickets(Train train);

    Set<CarrageType> getTypesOfCarrages(Train train);

    Train getTrainById(long trainid);

    void buyTicket(long id, int numberOfCarrage, String numberOfSeat, User user);


    List<Ticket> getTicketsByUser(User user);

    void returnTicketById(long id);
}
