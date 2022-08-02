package com.clickbus.placesApi.config.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value =  {MethodArgumentNotValidException.class, ValidationException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public List<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

		List<ErrorMessage> dtoList = new ArrayList<ErrorMessage>();
		List<FieldError> fieldErrors = ex.getFieldErrors();
		
		fieldErrors.forEach(e -> {
			ErrorMessage erro = new ErrorMessage(e.getField(), e.getDefaultMessage());
			dtoList.add(erro);
		});
		
		return dtoList;
	}
}