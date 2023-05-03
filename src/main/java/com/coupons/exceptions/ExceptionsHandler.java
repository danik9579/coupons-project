package com.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;

import com.coupons.dto.ErrorDto;
import com.coupons.enums.ErrorType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	@ResponseBody
	public ErrorDto toResponse(Throwable throwable, HttpServletResponse response) {

		if(throwable instanceof ServerException) {

			ServerException serverException = (ServerException) throwable;

			ErrorType errorType = serverException.getErrorType();
			int errorCode = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage();
			response.setStatus(errorCode);

			if(serverException.getErrorType().isShowStackTrace()) {
				serverException.printStackTrace();
			}

			ErrorDto errorDto = new ErrorDto(errorCode, errorMessage);
			return errorDto;
		}
		
		response.setStatus(600);

		String errorMessage = "Something went wrong, try again later!";
		ErrorDto errorDto = new ErrorDto(600, errorMessage);
		throwable.printStackTrace();

		return errorDto;
	}

}


