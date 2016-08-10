package com.pgrabarczyk.reservations.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pgrabarczyk.reservations.exception.RecordNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public abstract class AbstractRESTController {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public void missingParamterHandler(Exception ex, HttpServletRequest request, HttpServletResponse response)
	    throws IOException {
	log.info(ex.toString());
	response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void missingParamterHandler(MethodArgumentTypeMismatchException ex) {
	log.info(ex.toString());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    public void recordNotFoundHandler(Exception ex, HttpServletRequest request, HttpServletResponse response)
	    throws IOException {
	log.info(ex.toString());
	response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
    }

}
