package de.fynnberger.backend.todo;

import de.fynnberger.backend.employee.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo")
    public ResponseEntity<Void> createTodo(@RequestBody CreationTodoDTO creationTodoDTO) {
        try {
            todoService.createTodo(creationTodoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todo/{id}")
    public ResponseEntity<Void> markCompletedAs(@PathVariable long id, @RequestParam boolean markCompletedAs) {
        try {
            todoService.markCompletedAs(id, markCompletedAs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TodoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable long id) {
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TodoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todo/employee/{employeeId}")
    public ResponseEntity<List<TodoDTO>> getAllTodosByUserId(@PathVariable long employeeId) {
        try {
            List<TodoDTO> todos = todoService.getAllTodosByEmployeeId(employeeId);
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } catch (TodoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}