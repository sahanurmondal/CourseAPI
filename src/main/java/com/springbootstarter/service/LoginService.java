package com.springbootstarter.service;


import com.springbootstarter.bean.Users;
import com.springbootstarter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class LoginService  implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(username, user.getPassword(), true, true, true,
                true, AuthorityUtils.createAuthorityList(user.getRole()));

    }

    public Users validateUser(String username) {
        Users user = usersRepository.findById(username).orElse(null);
        return user;
    }

    @PostConstruct
    public void loadUsers() {
        List<Users> users = Arrays.asList(
                new Users("user123", "{noop}password", "USER"),
                new Users("admin123", "{noop}password", "ADMIN"));
        usersRepository.saveAll(users);
    }
}