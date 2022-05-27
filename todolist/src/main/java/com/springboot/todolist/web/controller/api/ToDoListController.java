package com.springboot.todolist.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.dto.ToDoListReqDto;
import com.springboot.todolist.dto.ToDoListRespDto;
import com.springboot.todolist.service.ToDoListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ToDoListController {
	
	private final ToDoListService toDoListService;
	
	//리스트 전체 불러오기
	@GetMapping("/todo/list")
	public ResponseEntity<?> getListAll() {
		List<ToDoListRespDto> listDtos = toDoListService.getListAll();
		return new ResponseEntity<>(listDtos, HttpStatus.OK);
	}
	
	//내용 추가
	@PostMapping("/todo")
	public ResponseEntity<?> addToDo(@Valid @RequestBody ToDoListReqDto toDoListReqDto) {
		toDoListService.addToDoList(toDoListReqDto.toListEntity());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//내용 수정
	@PutMapping("/todo/{id}")
	public ResponseEntity<?> modifiTodo(@PathVariable int id, @RequestBody ToDoListReqDto toDoListReqDto) {
		if(toDoListService.updateToDoList(toDoListReqDto.toListEntity())) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//내용 삭제
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> removeToDo(@PathVariable int id) {
		if(toDoListService.deleteList(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
