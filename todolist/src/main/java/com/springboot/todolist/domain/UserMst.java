package com.springboot.todolist.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMst {
	private int usercode;
	private String name;
	private String username;
	private String password;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}
