package com.mynewproject.mynewproject.repositories;

import com.mynewproject.mynewproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
