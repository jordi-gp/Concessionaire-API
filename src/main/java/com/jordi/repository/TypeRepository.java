package com.jordi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jordi.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

}
