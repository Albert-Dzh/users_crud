package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.UserItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserItemRepository extends CrudRepository<UserItem, Integer> {

    List<UserItem> findAllByUserIdOrderById(int id);
}
