package de.fynnberger.backend.todo;

import de.fynnberger.backend.employee.Employee;
import de.fynnberger.backend.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final EmployeeRepository employeeRepository;

    public TodoService(TodoRepository todoRepository, EmployeeRepository employeeRepository) {
        this.todoRepository = todoRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<TodoDTO> getAllTodosByEmployeeId(long employeeId) throws TodoNotFoundException {
        List<Todo> todos = todoRepository.findAllByEmployeeId(employeeId);
        if (todos.isEmpty()) {
            throw new TodoNotFoundException();
        }
        return todos.stream()
                .map(todo -> new TodoDTO(todo.getId(), todo.getTitle(), todo.isCompleted(), todo.getEmployee().getId()))
                .collect(Collectors.toList());
    }

    public void createTodo(CreationTodoDTO givenTodo) {
        Employee employee = employeeRepository.findById(givenTodo.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));
        Todo todo = new Todo(givenTodo, employee);
        todoRepository.save(todo);
    }

    public void markCompletedAs(long id, boolean markCompleteAs) throws TodoNotFoundException {
        Todo todo = todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        todo.setCompleted(markCompleteAs);
        todoRepository.save(todo);
    }

    public void deleteTodoById(long id) throws TodoNotFoundException {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException();
        }
        todoRepository.deleteById(id);
    }
}