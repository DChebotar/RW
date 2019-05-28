package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rw.entity.User;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User findUserByLogin(String loginFromPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> q =  session.createQuery("from User where login = :pagelogin", User.class);
        q.setParameter("pagelogin", loginFromPage);
        return q.list().stream().findAny().orElse(null);
    }

    public boolean saveUser(User user) {
        if (findUserByLogin(user.getLogin()) != null){
            return false;
        }
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return true;
    }
}
