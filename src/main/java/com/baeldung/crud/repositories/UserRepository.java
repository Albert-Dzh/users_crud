package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.User;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Page<User> findAllByLogin(String login, Pageable pageable);
    Page<User> findAllByOrderById(Pageable pageable);
}
