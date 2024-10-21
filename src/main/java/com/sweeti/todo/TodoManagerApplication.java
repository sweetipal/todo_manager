package com.sweeti.todo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sweeti.todo.dao.TodoDao;
import com.sweeti.todo.models.Todo;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	@Autowired
	private TodoDao todoDao;
	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("appplication started: ");
//		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("Template object: {}",template);
		
//		Todo todo = new Todo();
//		todo.setId(1230);
//		todo.setTitle("Java placement course");
//		todo.setContent("I have to learn java course");
//		todo.setStatus("pending");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//		todoDao.saveTodo(todo);
		
//		Todo todo =todoDao.getTodo(1230);
//		logger.info("todo data {}",todo);
//		todo.setTitle("Learn Springboot");
//		todo.setContent("jdbc");
//		todo.setStatus("done");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//		
//		todoDao.updateTodo(1230, todo);
		
//		List<Todo> todos = todoDao.getAllTodos();
//		logger.info("All todos {}", todos);
		
	//	todoDao.deleteTodo(1230);
	}

}
