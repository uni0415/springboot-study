package com.springboot.todolist.domain;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToDoListRepository {
	public int addToDoList(ToDoListMst toDoListMst);
	public List<Map<String, Object>> getListAll(int usercode);
	public int updateToDoList(ToDoListMst toDoListMst);
	public int deleteList(int id);
	
}
