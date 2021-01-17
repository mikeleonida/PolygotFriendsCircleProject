package com.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
