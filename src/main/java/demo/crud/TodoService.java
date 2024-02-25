package demo.crud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int count = 0;
	
	static {
		todos.add(new Todo(++count, "in28minutes", "Learn 1", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++count, "in28minutes", "Learn 2", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++count, "in28minutes", "Learn 3", LocalDate.now().plusYears(3), true));

	}
	
	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		 return todos.stream()
	                .filter(predicate)
	                .toList();
	}
	
	public void addTodo(String username, String name, LocalDate dueDate, boolean isDone) {
		Todo todo = new Todo(++count, username, name, dueDate, isDone);
		todos.add(todo);	
	}
	
	public void deleteById(Integer id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(Integer id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
}
