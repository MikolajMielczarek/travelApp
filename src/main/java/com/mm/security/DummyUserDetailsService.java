package com.mm.security;

import com.mm.security.model.Role;
import java.util.Arrays;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DummyUserDetailsService implements UserDetailsService {

    // TODO configure if loading users from DB in the future;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "userPass", Arrays.asList(Role.USER));
    }
}
