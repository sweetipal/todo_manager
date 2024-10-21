package com.sweeti.todo.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweeti.todo.models.Todo;
import com.sweeti.todo.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoService todoService;

	private final Random random = new Random();
//	String str = null;

	// create todo
	@PostMapping
	public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
		int id = random.nextInt(9999999);
		todo.setId(id);
		// create date with system default current date
		Date currentDate = new Date();
		logger.info("current date {}", currentDate);
//		logger.info("String length {}", str.length());
		todo.setAddedDate(currentDate);
		// create todo
		logger.info("Creating Todos...");
		logger.info("Todo date {}", todo.getTodoDate());
		// call service to create todo
		Todo createdTodo = todoService.createTodo(todo);
		return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
	}

// get All todo
	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodoHandler() {
		List<Todo> allTodos = todoService.getAllTodos();
		return new ResponseEntity<>(allTodos, HttpStatus.OK);
	}

// get single todo
	@GetMapping("/{todoId}")
	public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable("todoId") int todoId) {
		Todo todo = todoService.getTodo(todoId);
		if (todo != null) {
			return new ResponseEntity<>(todo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// todo update handler
	@PutMapping("/{todoId}")
	public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId) {
		Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
		return ResponseEntity.ok(todo);
	}

	// todo delete handler
	@DeleteMapping("/{todoId}")
	public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId) {
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo successfully deleted.");
	}

	// defining exception handler
//	@ExceptionHandler(value= {NullPointerException.class,NumberFormatException.class})
//	public ResponseEntity<String> nullPointerExceptionHandler(Exception ex) {
//		System.out.println(ex.getMessage());
//		System.out.println("Null pointer exception gernerated");
//		return new ResponseEntity<>("Null pointer exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}

//	@ExceptionHandler(NumberFormatException.class)
//	public ResponseEntity<String> numberFormatExceptionHandler(NumberFormatException ex) {
//		System.out.println(ex.getMessage());
//		System.out.println("Number format exception gernerated");
//		return new ResponseEntity<>("Number format exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}

}
