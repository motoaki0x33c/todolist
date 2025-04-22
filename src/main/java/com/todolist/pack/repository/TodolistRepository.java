package com.todolist.pack.repository;

import com.todolist.pack.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Long> {
    // JpaRepository 會自動實作好 CRUD 方法
}
