package com.sweeti.todo.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sweeti.todo.helper.Helper;
import com.sweeti.todo.models.Todo;

@Component
public class TodoDao {

	private JdbcTemplate template;
	Logger logger = LoggerFactory.getLogger(TodoDao.class);

	public TodoDao(@Autowired JdbcTemplate template) {
		this.template = template;
		// create table if does not exists
		String createTable = "create table IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(500),status varchar(10)not null,addedDate datetime,todoDate datetime)";
		int update = template.update(createTable);
		logger.info("Todo table created {}", update);

	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// save todo in db
	public Todo saveTodo(Todo todo) {
		String insertQuery = "insert into todos(id,title,content,status,addedDate,todoDate)values(?,?,?,?,?,?)";
		int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(),
				todo.getAddedDate(), todo.getTodoDate());
		logger.info("jdbc opertaion: {} inserted", rows);
		return todo;
	}

	// get single todo
	public Todo getTodo(int id) {
		String query = "select * from todos where id =?";
		Map<String, Object> todoData = template.queryForMap(query, id);
		logger.info("ToDo {}", todoData);
		Todo todo = new Todo();
		todo.setId((int) todoData.get("id"));
		todo.setTitle((String) todoData.get("title"));
		todo.setContent((String) todoData.get("content"));
		todo.setStatus((String) todoData.get("status"));
		todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
		todo.setTodoDate(Helper.parseDate((LocalDateTime) todoData.get("tododate")));

		return todo;
	}

	// get all todo
	public List<Todo> getAllTodos() {
		String query = "select * from todos";
		List<Map<String, Object>> maps = template.queryForList(query);
		List<Todo> todos = maps.stream().map((map) -> {
			Todo todo = new Todo();
			todo.setId((int) map.get("id"));
			todo.setTitle((String) map.get("title"));
			todo.setContent((String) map.get("content"));
			todo.setStatus((String) map.get("status"));
			todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
			todo.setTodoDate(Helper.parseDate((LocalDateTime) map.get("tododate")));
			return todo;
		}).collect(Collectors.toList());
		return todos;
	}

	// update todo

	public Todo updateTodo(int id, Todo newTodo) {
		String query = "update todos set title=?, content=?, status=?, addedDate=?, todoDate=? where id=?";
		int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(),newTodo.getAddedDate(), newTodo.getTodoDate(),id);
		logger.info("Updated values: {}",update);
		newTodo.setId(id);
		return newTodo;
	}
	
	//delte todo 
	public void deleteTodo(int id) {
		String query = "delete from todos where id =?";
		int update = template.update(query,id);
		logger.info("deleted {}",update);
		
	}
	
	public void deleteMultiple(int ids[]) {
		String query = "delete from todos where id=?";
		
	}
}
