package com.todo1.kardex.repositories;

import com.todo1.kardex.models.Toy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ToyRepository extends CrudRepository<Toy, Long> {
    List<Toy> findById(@Param("id") String id);
}
