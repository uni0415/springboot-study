package com.springboot.study.domain.board;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@NotNull
@AllArgsConstructor
@Data
public class BoardMst {
	private int board_code;
	private String board_title;
	private String board_content;
	private int board_writer;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	
}
