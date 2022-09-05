package com.users.crud.repositories;

import com.users.crud.entities.UserItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserItemRepository extends CrudRepository<UserItem, Integer> {

    Page<UserItem> findAllByUserIdOrderById(int id, Pageable pageable);
}
