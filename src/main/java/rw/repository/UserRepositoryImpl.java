package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import rw.entity.User;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDetails findByUsername(String login) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, login);
    }
}
