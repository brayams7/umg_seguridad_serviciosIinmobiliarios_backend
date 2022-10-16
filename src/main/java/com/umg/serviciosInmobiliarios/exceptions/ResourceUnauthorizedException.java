package com.umg.serviciosInmobiliarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends Exception{

    public ResourceUnauthorizedException(String message){
        super(message);
    }
}
