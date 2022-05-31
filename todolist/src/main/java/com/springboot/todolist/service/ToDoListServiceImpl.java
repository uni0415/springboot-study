package com.springboot.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todolist.domain.ToDoList;
import com.springboot.todolist.domain.ToDoListRepository;
import com.springboot.todolist.dto.ToDoListRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

	private final ToDoListRepository toDoListRepository;

	@Override
	public boolean addToDoList(ToDoList todoList) {
		return toDoListRepository.addToDoList(todoList) > 0;
	}

	@Override
	public List<ToDoListRespDto> getListAll(int usercode) {
		List<ToDoListRespDto> listDtos = new ArrayList<ToDoListRespDto>();
		List<ToDoList> listAll = toDoListRepository.getListAll(usercode);

		for (ToDoList list : listAll) {
			listDtos.add(list.toListDto());
		}
		return listDtos;

	}

	@Override
	public boolean updateToDoList(ToDoList toDoList) {
		return toDoListRepository.updateToDoList(toDoList)>0;
	}
	
	@Override
	public boolean deleteList(int id) {
		return toDoListRepository.deleteList(id)>0;
	}
	
	@Override
	public boolean signinToDo() {
		return false;
	}
}
