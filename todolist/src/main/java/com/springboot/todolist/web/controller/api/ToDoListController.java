package com.springboot.todolist.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.annotation.app.Validation;
import com.springboot.todolist.auth.PrincipalDetails;
import com.springboot.todolist.dto.CMRespDto;
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
	@GetMapping("/user/todo/list")
	public ResponseEntity<?> getListAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		int usercode = principalDetails.getUsercode();
		System.out.println(usercode);
		List<ToDoListRespDto> listDtos = toDoListService.getListAll(usercode);
		System.out.println(listDtos);
		return new ResponseEntity<>(new CMRespDto<List<ToDoListRespDto>>(1, "리스트 불러오기 성공", listDtos), HttpStatus.OK);
	}
	
	//내용 추가
	@Validation
	@PostMapping("/todo")
	public ResponseEntity<?> addToDo(@Valid @RequestBody ToDoListReqDto toDoListReqDto,
									 @AuthenticationPrincipal PrincipalDetails principalDetails) {
		toDoListReqDto.setUsercode(principalDetails.getUsercode());
		boolean result = toDoListService.addToDoList(toDoListReqDto.toListEntity());
		if(result == true) {
			return new ResponseEntity<>(new CMRespDto<>(1, "내용 추가 성공", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CMRespDto<>(-1, "빈값", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	//내용 수정
	@Validation
	@PutMapping("/todo/{id}")
	public ResponseEntity<?> modifiTodo(@PathVariable int id, @Valid @RequestBody ToDoListReqDto toDoListReqDto) {
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
