package com.springboot.todolist.dto;

import javax.validation.constraints.NotBlank;

import com.springboot.todolist.domain.ToDoListMst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoListReqDto {
	private int id;
	private int usercode;
	@NotBlank
	private String content;
	
	public ToDoListMst toListEntity() {
		return ToDoListMst.builder()
				.id(id)
				.usercode(usercode)
				.content(content)
				.build();
	}
	
}
