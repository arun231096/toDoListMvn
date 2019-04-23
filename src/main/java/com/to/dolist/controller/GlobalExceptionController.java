package com.to.dolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.to.dolist.utilities.AppException;
import com.to.dolist.utilities.ErrorCodes;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(AppException.class)
	public ModelAndView handleException(AppException e) {
		ModelAndView model = new ModelAndView("error");
		model.setStatus(HttpStatus.BAD_REQUEST);
		model.addObject("errCode", e.getErrorCodes());
		String errors = "";
		for (ErrorCodes er : e.getErrorCodes()) {
			errors = errors + " <br> " + er.getErrorMeggage();
		}
		model.addObject("errMsg", errors);
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("error");
		model.setStatus(HttpStatus.BAD_GATEWAY);
		model.addObject("errMsg", "INTERNAL SERVER ERROR");
		return model;

	}
}
