package com.excellence.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excellence.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
