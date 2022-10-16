package com.umg.serviciosInmobiliarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequest extends RuntimeException{

    public ResourceBadRequest(String message){
        super(message);
    }
}
