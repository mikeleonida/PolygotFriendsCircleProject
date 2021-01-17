package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.UserCircle;

@Repository
public interface UserCircleRepository extends JpaRepository<UserCircle, Integer> {

}
