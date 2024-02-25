package demo.crud;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository repository;
	
	@Autowired
	private AuthenService authentication;

	public TodoControllerJpa(TodoRepository repository, TodoService service, AuthenService authentication) {
		super();
		this.repository = repository;
		this.authentication = authentication;
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", "in28minutes");
		return "welcome";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@PostMapping("login")
	public String welcomePage(@RequestParam String username, @RequestParam String password, ModelMap model) {
		//login? name&pass
		if (authentication.authenticate(username, password)) {
			model.put("username", username);
			model.put("password", password);	
			return "welcome";			
		}
		model.put("errorMessage", "Invalid Credentials! Try again");
		return "login";
	}
	
	@GetMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> list = repository.findByUsername("in28minutes");
		model.addAttribute("todos", list);
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String) model.getAttribute("username");
		Todo todo = new Todo(0, username, "Default desc", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "addTodo";
	}
	
	@PostMapping("add-todo")
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addTodo";
		}

		String username = (String)model.get("username");
		if (username == null) {
			todo.setUsername("in28minutes");
		}
		else {
			todo.setUsername(username);
		}
		
		repository.save(todo);
		
//		service.addTodo(username, todo.getName(), todo.getDueDate(), todo.isDone());
		return "redirect:list-todos";
	}
	
	@GetMapping("delete-todo")
	public String deleteTodos(@RequestParam Integer id) {
		repository.deleteById(id);
//		service.deleteById(id);
		return "redirect:list-todos";
	}
	
	@GetMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam Integer id, ModelMap model) {
		Todo todo = repository.findById(id).get();
		model.addAttribute("todo", todo);
		return "addTodo";
	}
	
	@PostMapping("update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addTodo";
		}

		String username = (String)model.get("username");
		if (username == null) {
			todo.setUsername("in28minutes");
		}
		else {
			todo.setUsername(username);
		}
		
		repository.save(todo);
//		service.updateTodo(todo);
		return "redirect:list-todos";
	}
}
