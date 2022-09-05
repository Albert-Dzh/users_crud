package com.users.crud.repositories;

import com.users.crud.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Page<User> findAllByLoginContainsIgnoreCaseOrderById(String login, Pageable pageable);
    Page<User> findAllByOrderById(Pageable pageable);
}
