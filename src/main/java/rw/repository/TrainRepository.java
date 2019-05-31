package rw.repository;

import rw.entity.Ticket;
import rw.entity.Train;
import rw.entity.User;

import java.util.List;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface TrainRepository {

    List<Train> getAllTrains();

    void saveTrain(Train train);

    Train getTrainById(long trainid);

    void saveTicket(Ticket ticket);

    List<Ticket> getTicketsByUser(User user);

    Ticket getTicketById(long id);

    void deleteTicket(long id);
}
