package org.loushang.framework.exception;

import org.loushang.framework.base.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestResult<String> handlerAllException(Exception ex) {
		return new RestResult<String>("500", ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private RestResult<String> illegalParamsExceptionHandler(Exception ex) {
		return new RestResult<String>("500", ex.getMessage());
	}

}
