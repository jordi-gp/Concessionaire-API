package com.jordi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jordi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT id, username,'' as pass ,'' as salt, role_id FROM users;", nativeQuery = true)
	List<User> findAllSanitized();
	
	User findByUsername(String username);
}
