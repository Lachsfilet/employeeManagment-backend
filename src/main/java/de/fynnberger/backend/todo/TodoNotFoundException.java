package de.fynnberger.backend.todo;

public class TodoNotFoundException extends Exception {
    public TodoNotFoundException() {
        super("Todo not found");
    }
}
