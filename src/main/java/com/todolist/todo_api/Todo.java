package com.todolist.todo_api;

import jakarta.persistence.*; // JPA 어노테이션들
import lombok.AccessLevel;   // Lombok의 AccessLevel
import lombok.Builder;      // Builder 어노테이션
import lombok.Getter;       // Getter 어노테이션
import lombok.NoArgsConstructor; // NoArgsConstructor 어노테이션
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Boolean completed;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void toggleComplete() {
        this.completed = !this.completed;
    }
}
