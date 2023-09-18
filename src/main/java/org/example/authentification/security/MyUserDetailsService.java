package org.example.authentification.security;

import org.example.authentification.entity.User;
import org.example.authentification.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

//    @Override
    public User loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findById(Long.valueOf(id));

        if (userRes.isEmpty()) {
            throw new UsernameNotFoundException("Could not findUser with id = " + id);
        }

        User user = userRes.get();
        user.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
