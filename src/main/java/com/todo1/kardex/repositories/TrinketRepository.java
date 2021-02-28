package com.todo1.kardex.repositories;

import com.todo1.kardex.models.Trinket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface TrinketRepository extends CrudRepository<Trinket, Long> {
    List<Trinket> findById(@Param("id") String id);
}
