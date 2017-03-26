package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {

}
