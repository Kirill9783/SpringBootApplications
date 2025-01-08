package com.example.spring.services;

import com.example.spring.dto.CommentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeMap;

@Service
public class CommentCRUDService implements CRUDService<CommentDto> {

    @Value("${comment.length.max}")
    private Integer maxLength;
    private final TreeMap<Integer, CommentDto> storage = new TreeMap<>();

    @Override
    public CommentDto getById(Integer id) {
        System.out.println("Get by ID: " + id);
        return storage.get(id);
    }

    @Override
    public Collection<CommentDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public void create(CommentDto item) {
        System.out.println("Create");
        int nextId = (storage.isEmpty() ? 0 : storage.lastKey()) + 1;
        item.setId(nextId);
        if (item.getText().length() > maxLength) {
            throw new RuntimeException("Comment is too long");
        }
        storage.put(nextId, item);
    }

    @Override
    public void update(Integer id, CommentDto item) {
        System.out.println("Update " + id);
        if (!storage.containsKey(id)) {
            return;
        }
        if (item.getText().length() > maxLength) {
            throw new RuntimeException("Comment is too long");
        }
        item.setId(id);
        storage.put(id, item);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Delete " + id);
        storage.remove(id);
    }
}
