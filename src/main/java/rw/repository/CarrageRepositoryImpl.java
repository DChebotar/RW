package rw.repository;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.AbstractCarrage;
import rw.entity.PassangerCarrage;
import rw.entity.Seat;
import rw.entity.Train;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Chebotar_do on 28.05.2019.
 */

@Repository
public class CarrageRepositoryImpl implements CarrageRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void saveCarrage(AbstractCarrage carrage) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(carrage);
    }

    public void saveSeat(Seat seat) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(seat);
    }

    public List<PassangerCarrage> getCarragesByTrain(Train train) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("SELECT CARRAGE_ID, CARRAGE_TYPE, NUMBER_OF_SEATS, TRAIN_ID, NUMBER_OF_CARRAGE FROM CARRAGES WHERE TRAIN_ID = :train");
        query.setParameter("train", train.getId());
        query.addEntity(PassangerCarrage.class);
        //Query<PassangerCarrage> query = session.createQuery("FROM AbstractCarrage c WHERE c.train.id = :train", PassangerCarrage.class);
        //query.setParameter("train", train.getId());
        List<PassangerCarrage> carrages = query.list();
        return carrages;
    }

    public List<Seat> getSeats(PassangerCarrage carrage) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery<Seat> sqlQuery = session.createSQLQuery("SELECT * FROM SEATS WHERE CARRAGE_ID = :carrageId");
        //Query<Seat> seatQuery = session.createQuery("FROM Seat WHERE carrage = :carrageId", Seat.class);
        sqlQuery.setParameter("carrageId", carrage.getId());
        sqlQuery.addEntity(Seat.class);
        return sqlQuery.list();
    }

    public PassangerCarrage getCarrageByTrainAndCarrageNumber(Train train, int carrageNumber) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery<PassangerCarrage> carrageQuery = session.createSQLQuery("SELECT * FROM CARRAGES WHERE TRAIN_ID = :train AND NUMBER_OF_CARRAGE = :number");
        carrageQuery.setParameter("train", train.getId());
        carrageQuery.setParameter("number", carrageNumber);
        carrageQuery.addEntity(PassangerCarrage.class);
        return carrageQuery.list().stream().findAny().orElse(null);
    }

    public Seat getSeatByCarrageAndNumber(PassangerCarrage carrage, String numberOfSeat) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery<Seat> seatSQLQuery = session.createSQLQuery("SELECT * FROM SEATS WHERE CARRAGE_ID = :carId AND SEAT_NUMBER = :number");
        seatSQLQuery.setParameter("carId", carrage.getId());
        seatSQLQuery.setParameter("number", numberOfSeat);
        seatSQLQuery.addEntity(Seat.class);
        return seatSQLQuery.list().stream().findAny().orElse(null);
    }
}
