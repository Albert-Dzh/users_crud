package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.ItemTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemTemplateRepository extends CrudRepository<ItemTemplate, Integer> {

    List<ItemTemplate> findAllByOrderById();
}
