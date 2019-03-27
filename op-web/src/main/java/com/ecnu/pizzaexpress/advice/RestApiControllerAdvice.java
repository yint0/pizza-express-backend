package com.ecnu.pizzaexpress.advice;

import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.exception.AuthenticationFailureException;
import com.ecnu.pizzaexpress.exception.InvalidParamException;
import com.ecnu.pizzaexpress.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by yerunjie on 2018/4/22
 *
 * @author yerunjie
 */
@RestControllerAdvice(assignableTypes = BaseController.class)
public class RestApiControllerAdvice {

  private final static Logger logger = LoggerFactory.getLogger(RestApiControllerAdvice.class);

  @ExceptionHandler
  public ResponseEntity handleException(AuthenticationFailureException ex) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler
  public ResponseEntity handleException(ResourceNotFoundException ex) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity handleException(InvalidParamException ex) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity handleException(DataAccessException ex) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity<>("invalid parameter", HttpStatus.BAD_REQUEST);
  }
}
