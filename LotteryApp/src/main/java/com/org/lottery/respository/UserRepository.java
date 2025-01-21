package com.org.lottery.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.lottery.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
