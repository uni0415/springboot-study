package com.springboot.todolist.service;

import java.util.List;

import com.springboot.todolist.domain.ToDoList;
import com.springboot.todolist.dto.ToDoListRespDto;

public interface ToDoListService {

	public boolean addToDoList(ToDoList toDoList);
	public List<ToDoListRespDto> getListAll(int usercode);
	public boolean updateToDoList(ToDoList toDoList);
	public boolean deleteList(int id);
	public boolean signinToDo();
}
