package com.jordi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jordi.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
