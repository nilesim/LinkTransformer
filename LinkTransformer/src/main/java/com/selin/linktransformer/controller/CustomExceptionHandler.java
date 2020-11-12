package com.selin.linktransformer.controller;

import com.selin.linktransformer.model.ErrorResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler
{
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MalformedURLException.class)
  public final ResponseEntity<Object> handleURLNotCorrectException(MalformedURLException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("URL is not correct", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnsupportedEncodingException.class)
  public final ResponseEntity<Object> handleUnsupportedEncodingException(UnsupportedEncodingException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("URL encoding is not correct", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("URL does not correspond any link", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
}