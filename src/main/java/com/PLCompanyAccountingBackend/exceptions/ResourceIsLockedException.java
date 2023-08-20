package com.PLCompanyAccountingBackend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED)
public class ResourceIsLockedException extends RuntimeException {
    public ResourceIsLockedException(String msg) {
        super(msg);
    }
}
