package com.todo1.kardex.repositories;

import com.todo1.kardex.models.Glass;
import com.todo1.kardex.models.Shirt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface GlassRepository extends CrudRepository<Glass, Long> {
    List<Shirt> findById(@Param("id") String id);
}
