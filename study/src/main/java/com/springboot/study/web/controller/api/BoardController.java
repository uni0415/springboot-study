package com.springboot.study.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.service.board.BoardService;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("/board/list")
	public ResponseEntity<?> getBoardList(int page) throws Exception {
		List<BoardRespDto> boardRespDtos = boardService.getBoardListByPage(page);
		return new ResponseEntity<>(new CMRespDto<List<BoardRespDto>>(1, "게시글 목록 로드", boardRespDtos),HttpStatus.OK);
	}
	
	@GetMapping("/board/list/count")
	public String getBoardListCount() throws Exception {
		return Integer.toString(boardService.getBoardListCount());
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> createBoard(@Valid @RequestBody BoardInsertReqDto boardInsertReqDto, BindingResult bindingResult) throws Exception{
		System.out.println(boardInsertReqDto);
		int board_code = boardService.createBoard(boardInsertReqDto);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 작성 완료", board_code), HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/board/{boardCode}")
	public ResponseEntity<?> getBoard(@PathVariable int boardCode) throws Exception {
		System.out.println(boardCode);
        BoardRespDto boardRespDto =  boardService.getBoard(boardCode);
		return new ResponseEntity<>(new CMRespDto<>(1, "게시글 조회 성공", boardRespDto), HttpStatus.OK);
	}
	
	@PutMapping("/board/{boardCode}")
	public ResponseEntity<?> updateBaord(@PathVariable int boardCode, 
			@Valid BoardUpdateReqDto boardUpdateReqDto, BindingResult bindingResult) throws Exception {
		int resultBoardCode = boardService.updateBoard(boardCode, boardUpdateReqDto);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 수정 성공", resultBoardCode), HttpStatus.OK);
	}
	
	@DeleteMapping("/board/{boardCode}")
	public ResponseEntity<?> deleteBoard(@PathVariable int boardCode) throws Exception {
		int resultBoardCode = boardService.deleteBoard(boardCode);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 삭제 성공", resultBoardCode), HttpStatus.OK);
	}
}
