package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.Route;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Repository
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Set<Route> getAllRouts(){
        Session session = sessionFactory.getCurrentSession();
        Query<Route> query = session.createQuery("FROM Route");
        return new HashSet<Route>(query.list());
    }

    public void saveRoute(Route route) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(route);
    }
}
