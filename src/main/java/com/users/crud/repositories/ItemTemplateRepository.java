package com.users.crud.repositories;

import com.users.crud.entities.ItemTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemTemplateRepository extends CrudRepository<ItemTemplate, Integer> {

    List<ItemTemplate> findAllByOrderById();
}
