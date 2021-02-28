package com.todo1.kardex;

import com.todo1.kardex.models.Item;
import com.todo1.kardex.repositories.ShirtRepository;
import com.todo1.kardex.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("items")
public class ItemController {
    @Autowired
    ToyRepository toyRepository;
    @Autowired
    ShirtRepository shirtRepository;

    @GetMapping
    public @ResponseBody List<Item> getAllItems(){
        List<Item> result = new ArrayList<Item>();
        toyRepository.findAll().forEach(t -> {
            result.add(t);
        });
        shirtRepository.findAll().forEach(t -> {
            result.add(t);
        });
        return result;
    }

    @GetMapping("/buy")
    public @ResponseBody List<Item> buy(){
        List<Item> result = new ArrayList<Item>();
        toyRepository.findAll().forEach(t -> {
            result.add(t);
        });
        shirtRepository.findAll().forEach(t -> {
            result.add(t);
        });
        return result;
    }
}
