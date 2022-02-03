package com.ofbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofbusiness.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
