package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.Role;
import rw.entity.User;
import rw.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


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
                .User(user.getLogin(), user.getPassword(), authorities);
    }
}
