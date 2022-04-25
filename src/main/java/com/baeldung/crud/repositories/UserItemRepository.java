package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.UserItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserItemRepository extends CrudRepository<UserItem, Integer> {

    List<UserItem> findAllByUserId(int id);
}
