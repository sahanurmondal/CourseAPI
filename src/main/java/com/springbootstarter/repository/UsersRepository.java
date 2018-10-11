package com.springbootstarter.repository;

import com.springbootstarter.bean.Users;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

}
