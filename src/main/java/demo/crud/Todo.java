package demo.crud;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String username;
	@Size(min=10, message="Enter at least 10 chars")
	private String name;
	private LocalDate dueDate;
	private boolean isDone;
	
	public Todo() {
		super();
	}

	public Todo(Integer id, String username, String name, LocalDate dueDate, boolean isDone) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.dueDate = dueDate;
		this.isDone = isDone;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", name=" + name + ", dueDate=" + dueDate + ", isDone="
				+ isDone + "]";
	}
	
	
}
