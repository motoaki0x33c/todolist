package com.todolist.pack.service;

import com.todolist.pack.model.Todolist;
import com.todolist.pack.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistService {
    @Autowired  // 這會自動依賴注入
    private TodoRepository todoRepository;

    public Todolist createTodolist(Todolist todolist) {
        return todoRepository.save(todolist);
    }

    public List<Todolist> getAllTodoLists() {
        return todoRepository.findAll();
    }

    // 使用 Optional 類別可以表明這個值可能為 null
    public Optional<Todolist> getTodoListById(Long id) {
        return todoRepository.findById(id);
    }

    public Todolist updateTodolist(Long id, Todolist update_data) {
        return todoRepository.findById(id).map(data -> {
            data.setTitle(update_data.getTitle());
            data.setDescription(update_data.getDescription());
            data.setCompleted(update_data.getCompleted());
            return todoRepository.save(data);
        }).orElse(null);
    }

    public void deleteTodolist(Long id) {
        todoRepository.deleteById(id);
    }
}
