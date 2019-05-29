package rw.repository;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.AbstractCarrage;
import rw.entity.PassangerCarrage;
import rw.entity.Seat;
import rw.entity.Train;

import java.util.List;

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
        SQLQuery query = session.createSQLQuery("SELECT CARRAGE_ID, CARRAGE_TYPE, NUMBER_OF_SEATS, TRAIN_ID FROM CARRAGES");
        query.addEntity(PassangerCarrage.class);
        //Query<PassangerCarrage> query = session.createQuery("FROM AbstractCarrage c WHERE c.train.id = :train", PassangerCarrage.class);
        //query.setParameter("train", train.getId());
        List<PassangerCarrage> carrages = query.list();
        return carrages;
    }
}
