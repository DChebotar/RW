package rw.repository;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.Ticket;
import rw.entity.Train;
import rw.entity.User;

import java.util.*;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Repository
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Train> getAllTrains() {
        Session session = sessionFactory.getCurrentSession();
        Query<Train> query = session.createQuery("FROM Train", Train.class);
        return query.list();
    }

    public void saveTrain(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(train);
    }

    public Train getTrainById(long trainid) {
        Session session = sessionFactory.getCurrentSession();
        Query<Train> query = session.createQuery("FROM Train WHERE id = :trainid", Train.class);
        query.setParameter("trainid", trainid);
        return query.list().stream().findAny().orElse(null);
    }

    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ticket);
    }

    public List<Ticket> getTicketsByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery<Ticket> ticketQuery = session.createSQLQuery("SELECT * FROM TICKET WHERE USER_ID = :userId");
        ticketQuery.setParameter("userId", user.getId());
        ticketQuery.addEntity(Ticket.class);
        return ticketQuery.list();
    }

    public Ticket getTicketById(long id) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery<Ticket> ticketSQLQuery = session.createSQLQuery("SELECT * FROM TICKET WHERE TICKET_ID = :id");
        ticketSQLQuery.setParameter("id", id);
        ticketSQLQuery.addEntity(Ticket.class);
        return ticketSQLQuery.list().stream().findAny().orElse(null);
    }

    public void deleteTicket(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getTicketById(id));
    }
}
