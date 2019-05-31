package rw.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import rw.entity.User;

import java.time.LocalDate;

/**
 * Created by Chebotar_do on 24.05.2019.
 */
public interface UserService {

    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;

    boolean createUser(String login, String password, String name, String patronymic, String surname, String passportSeries, String passportNumber, LocalDate passportIssueDate, String passportIssueBy, String email);

    User getUserByLogin(String login);
}
