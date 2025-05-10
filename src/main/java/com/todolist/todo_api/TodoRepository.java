package com.todolist.todo_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompleted(Boolean completed);
    List<Todo> findByTitleContaining(String keyword);

    @Query("SELECT t FROM Todo t ORDER BY t.createdAt DESC ")
    List<Todo> findAllOrderByCreatedAt();
}
