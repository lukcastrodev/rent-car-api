package com.ctech.rentcarapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctech.rentcarapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
