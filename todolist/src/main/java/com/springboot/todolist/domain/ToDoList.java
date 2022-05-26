package com.springboot.todolist.domain;

import com.springboot.todolist.dto.ToDoListRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ToDoList {
	
	private int id;
	private int usercode;
	private String content;
	
	public ToDoListRespDto toListDto() {
		return ToDoListRespDto.builder()
				.id(id)
				.usercode(usercode)
				.content(content)
				.build();
	}
}
