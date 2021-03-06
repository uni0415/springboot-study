package com.springboot.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ToDoListRespDto {

	private int id;
	private int usercode;
	private String name;
	private String content;
}
