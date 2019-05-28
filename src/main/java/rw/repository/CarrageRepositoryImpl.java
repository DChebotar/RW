package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.AbstractCarrage;
import rw.entity.Seat;

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
}
