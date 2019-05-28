package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.Role;
import rw.entity.User;
import rw.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        if (user == null){
            throw new UsernameNotFoundException("User is not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return new org.springframework.security.core.userdetails
                .User(user.getLogin(),user.getPassword(), authorities);
    }

    public boolean createUser(String login, String password, String name, String patronymic, String surname,
                              String passportSeries, String passportNumber, LocalDate passportIssueDate, String passportIssueBy, String email) {
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
        return userRepository.saveUser(user);
    }


}
