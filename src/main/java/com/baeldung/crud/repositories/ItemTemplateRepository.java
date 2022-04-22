package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.ItemTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTemplateRepository extends CrudRepository<ItemTemplate, Integer> {

    List<ItemTemplate> findAllByOrderById();
    List<ItemTemplate> findAllByLvlOrderById(String lvl);
}
