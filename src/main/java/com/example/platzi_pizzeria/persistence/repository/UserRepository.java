package com.example.platzi_pizzeria.persistence.repository;

import com.example.platzi_pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
