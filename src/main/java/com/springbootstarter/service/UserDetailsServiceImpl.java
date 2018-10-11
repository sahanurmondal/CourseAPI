package com.springbootstarter.service;

import com.springbootstarter.bean.Users;
import com.springbootstarter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

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
	

	/**
	 * Add some users at application startup for testing
	 */
	@PostConstruct
	public void loadUsers() {
		List<Users> users = Arrays.asList(
							new Users("user", "{noop}password", "USER"),
							new Users("admin", "{noop}password", "ADMIN"));
		usersRepository.saveAll(users);
	}

}
