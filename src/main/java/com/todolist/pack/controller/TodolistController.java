package com.todolist.pack.controller;

import com.todolist.pack.model.Todolist;
import com.todolist.pack.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodolistController {
    @Autowired
    private TodolistService todolistService;

    // 新增代辦事項
    @PostMapping
    public Todolist createTodolist(@RequestBody Todolist todolist) {
        return todolistService.createTodolist(todolist);
    }

    // 查詢全部代辦
    @GetMapping
    public List<Todolist> getAllTodolists() {
        return todolistService.getAllTodoLists();
    }

    // 查詢單筆
    @GetMapping("/{id}")
    public Todolist getTodolist(@PathVariable Long id) {
        return todolistService.getTodoListById(id).orElse(null);
    }

    // 更新代辦
    @PutMapping("/{id}")
    public Todolist updateTodolist(@PathVariable Long id, @RequestBody Todolist todolist) {
        return todolistService.updateTodolist(id, todolist);
    }

    // 刪除代辦
    @DeleteMapping("/{id}")
    public void deleteTodolist(@PathVariable Long id) {
        todolistService.deleteTodolist(id);
    }
}
