package com.PLCompanyAccountingBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class RequestNotAcceptableException extends RuntimeException {
    public RequestNotAcceptableException(String msg) {
        super(msg);
    }
}
