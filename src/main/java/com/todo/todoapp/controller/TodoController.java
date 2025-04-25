package com.todo.todoapp.controller;

import com.todo.todoapp.model.Todo;
import com.todo.todoapp.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoRepository todoRepo;

    public TodoController(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo todo = todoRepo.findById(id).orElseThrow();
        todo.setTitle(updatedTodo.getTitle());
        todo.setCompleted(updatedTodo.isCompleted());
        return todoRepo.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepo.deleteById(id);
    }
}
