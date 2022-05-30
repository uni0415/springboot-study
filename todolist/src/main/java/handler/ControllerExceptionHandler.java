package handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.dto.CMRespDto;

import handler.exception.CustomValidationApiException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiExeption(CustomValidationApiException e)  {
		return new ResponseEntity<>(new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}
