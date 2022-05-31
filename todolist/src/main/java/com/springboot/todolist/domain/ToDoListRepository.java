package com.springboot.todolist.domain;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToDoListRepository {
	public int addToDoList(ToDoList toDoListMst);
	public List<ToDoList> getListAll(int usercode);
	public int updateToDoList(ToDoList toDoListMst);
	public int deleteList(int id);
	
}
