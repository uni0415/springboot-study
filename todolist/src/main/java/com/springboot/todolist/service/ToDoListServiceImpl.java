package com.springboot.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.todolist.domain.ToDoListMst;
import com.springboot.todolist.domain.ToDoListRepository;
import com.springboot.todolist.dto.ToDoListRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

	private final ToDoListRepository toDoListRepository;

	@Override
	public boolean addToDoList(ToDoListMst todoListMst) {
		return toDoListRepository.addToDoList(todoListMst) > 0;
	}

	@Override
	public List<ToDoListRespDto> getListAll(int usercode) {
		List<ToDoListRespDto> listDtos = new ArrayList<ToDoListRespDto>();
		List<Map<String, Object>> listAll = toDoListRepository.getListAll(usercode);

		for (Map<String, Object> listMap : listAll) {
			listDtos.add(ToDoListRespDto.builder()
					.id((Integer) (listMap.get("id")))
					.usercode((Integer) (listMap.get("usercode")))
					.name((String) (listMap.get("name")))
					.content((String) (listMap.get("content")))
					.build());
		}
		return listDtos;

	}

	@Override
	public boolean updateToDoList(ToDoListMst toDoListMst) {
		return toDoListRepository.updateToDoList(toDoListMst)>0;
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
