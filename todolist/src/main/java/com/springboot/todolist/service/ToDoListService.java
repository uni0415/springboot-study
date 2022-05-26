package com.springboot.todolist.service;

import java.util.List;

import com.springboot.todolist.domain.ToDoListMst;
import com.springboot.todolist.dto.ToDoListRespDto;

public interface ToDoListService {

	public boolean addToDoList(ToDoListMst toDoListMst);
	public List<ToDoListRespDto> getListAll();
	public boolean updateToDoList(ToDoListMst toDoListMst);
	public boolean deleteList(int id);
}
