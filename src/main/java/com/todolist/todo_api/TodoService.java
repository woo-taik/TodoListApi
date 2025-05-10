package com.todolist.todo_api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAllOrderByCreatedAt().stream().map(TodoResponseDto::new).collect(Collectors.toList());
    }

    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found"));

        return new TodoResponseDto(todo);
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = requestDto.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponseDto(savedTodo);
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 Todo를 찾지 못했습니다."));
        todo.update(requestDto.getTitle(), requestDto.getDescription());
        return new TodoResponseDto(todo);
    }

    public TodoResponseDto toggleTodoComplete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 Todo를 찾지 못했습니다"));
        todo.toggleComplete();
        return new TodoResponseDto(todo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("해당 Todo는 없습니다");
        }
        todoRepository.deleteById(id);
    }

    public List<TodoResponseDto> getTodoByCompleted(Boolean completed) {
        return todoRepository.findByCompleted(completed).stream().map(TodoResponseDto::new).collect(Collectors.toList());
    }

    public List<TodoResponseDto> searchTodos(String keyword) {
        return todoRepository.findByTitleContaining(keyword).stream().map(TodoResponseDto::new).collect(Collectors.toList());
    }
}
