package com.springboot.todolist.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ToDoListMst {

	private int id;
	private int usercode;
	private String content;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}
