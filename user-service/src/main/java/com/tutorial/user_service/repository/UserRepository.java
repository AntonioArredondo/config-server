package com.tutorial.user_service.repository;

import com.tutorial.user_service.entity.Userx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userx, Integer> {

}
