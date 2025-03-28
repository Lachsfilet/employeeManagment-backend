package de.fynnberger.backend.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByEmployeeId(Long employeeId);
    boolean existsByEmployeeId(Long employeeId);
}
