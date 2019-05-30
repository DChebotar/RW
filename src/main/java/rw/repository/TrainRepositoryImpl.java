package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.Train;

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
}
