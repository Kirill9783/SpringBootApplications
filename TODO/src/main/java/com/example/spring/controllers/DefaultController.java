package com.example.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

@RestController
public class DefaultController {

    private TreeMap<Integer, String> todo;

    public DefaultController() {
        todo = new TreeMap<>();
        todo.put(1, "Buy milk");
        todo.put(2, "Feed the cat");
    }

    @GetMapping(path = "/todo")
    public TreeMap<Integer, String> getTodo() {
        return todo;
    }

    @PostMapping(path = "/todo")
    public ResponseEntity post(@RequestParam String item) {
        if (item == null || item.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        todo.put(todo.lastKey() + 1, item);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/todo/{id}")
    public ResponseEntity put(@PathVariable int id, @RequestParam String item) {
        todo.put(id, item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "/todo/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        todo.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
