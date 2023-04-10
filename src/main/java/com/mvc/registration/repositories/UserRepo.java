package com.mvc.registration.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.registration.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
//	List<User> findAll();
	Optional<User> findByEmail(String email); // find any user with the given email - if any
}