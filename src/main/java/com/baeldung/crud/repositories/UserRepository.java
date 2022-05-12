package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Page<User> findAllByLoginContains(String login, Pageable pageable);
    Page<User> findAllByOrderById(Pageable pageable);
}
