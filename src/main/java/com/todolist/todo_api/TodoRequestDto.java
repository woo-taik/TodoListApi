package com.todolist.todo_api;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequestDto {
    @NotBlank
    private String title;

    private String description;

    @Builder
    public TodoRequestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Todo toEntity() {
        return Todo.builder().title(title).description(description).build();
    }
}
