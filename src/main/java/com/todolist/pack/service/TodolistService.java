package com.todolist.pack.service;

import com.todolist.pack.model.Todolist;
import com.todolist.pack.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistService {
    @Autowired  // 這會自動依賴注入
    private TodolistRepository todolistRepository;

    public Todolist createTodolist(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    public List<Todolist> getAllTodoLists() {
        return todolistRepository.findAll();
    }

    // 使用 Optional 類別可以表明這個值可能為 null
    public Optional<Todolist> getTodoListById(Long id) {
        return todolistRepository.findById(id);
    }

    public Todolist updateTodolist(Long id, Todolist update_data) {
        return todolistRepository.findById(id).map(data -> {
            data.setTitle(update_data.getTitle());
            data.setDescription(update_data.getDescription());
            data.setCompleted(update_data.getCompleted());
            return todolistRepository.save(data);
        }).orElse(null);
    }

    public void deleteTodolist(Long id) {
        todolistRepository.deleteById(id);
    }
}
