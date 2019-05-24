package rw.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import rw.entity.Role;
import rw.entity.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByLogin(String loginFromPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> q =  session.createQuery("from User where login = :pagelogin", User.class);
        q.setParameter("pagelogin", loginFromPage);
        return q.list().stream().findAny().orElse(null);
    }

    public boolean createUser(String login, String password, String name, String patronymic, String surname, String passportSeries, String passportNumber,
                              LocalDate passportIssueDate, String passportIssueBy, String email) {
        if (findUserByLogin(login) != null){
            return false;
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setSurname(surname);
        user.setPassportSeries(passportSeries);
        user.setPassportNumber(passportNumber);
        user.setPassportIssueDate(passportIssueDate);
        user.setPassportIssueBy(passportIssueBy);
        user.setEmail(email);
        user.setActive(true);
        Set<Role> roles = new HashSet<Role>();
        roles.add(Role.USER);
        user.setRoles(roles);

        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return true;
    }
}
