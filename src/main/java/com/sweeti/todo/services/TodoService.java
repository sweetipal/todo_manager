package com.sweeti.todo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sweeti.todo.exception.ResourceNotFoundException;
import com.sweeti.todo.models.Todo;

//@Component
@Service
public class TodoService {
	Logger logger = LoggerFactory.getLogger(TodoService.class);

	List<Todo> todos = new ArrayList<>();

	public Todo createTodo(Todo todo) {
		// create
		// change the logic'
		todos.add(todo);
		logger.info("todos {}", this.todos);
		return todo;
	}

	public List<Todo> getAllTodos() {
		return todos;

	}

	public Todo getTodo(int todoId) {
		Todo todo = todos.stream()
                .filter(t -> todoId == t.getId())
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("To do not found with given id", HttpStatus.NOT_FOUND));
		logger.info("todo {}", todo);
		return todo;
	}

	public Todo updateTodo(int todoId, Todo todo) {
		List<Todo> newUpdatedList = todos.stream().map(t->{
			if(t.getId()==todoId) {
				//update todo
				t.setTitle(todo.getTitle());
				t.setContent(todo.getContent());
				t.setStatus(todo.getStatus());
				return t;
			}else {
				return t;
			}
		}).collect(Collectors.toList());
		todos = newUpdatedList;
		todo.setId(todoId);
		
		return todo;
	}

	public void deleteTodo(int todoId) {
		logger.info("deleting todo...");
		List<Todo>newList = todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
		todos=newList;
	}

}
