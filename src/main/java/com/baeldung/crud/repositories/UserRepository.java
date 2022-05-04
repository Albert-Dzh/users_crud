package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.User;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAllByOrderById(Pageable pageable);
}
