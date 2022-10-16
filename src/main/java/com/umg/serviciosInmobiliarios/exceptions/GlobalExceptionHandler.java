package com.umg.serviciosInmobiliarios.exceptions;

import com.umg.serviciosInmobiliarios.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerExceptionNotFund(ResourceNotFoundException ex, WebRequest request){
        ErrorDto err = new ErrorDto(404,new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDto>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequest.class)
    public ResponseEntity<ErrorDto> handlerExceptionBadRequest(ResourceBadRequest ex, WebRequest request){
        ErrorDto err = new ErrorDto(400,new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDto>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceUnauthorizedException.class)
    public ResponseEntity<ErrorDto> handlerExceptionUnauthorized(ResourceUnauthorizedException ex, WebRequest request){
        ErrorDto err = new ErrorDto(401,new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDto>(err, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //cuando el valor de un atributo o lo que se está enviando no es válido.
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err ->{
            String nameAttribute = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            errors.put(nameAttribute, message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
