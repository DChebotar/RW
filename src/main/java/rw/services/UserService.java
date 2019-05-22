package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.repository.UserRepositoryImpl;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepositoryImpl userRepository;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login);
    }
}
