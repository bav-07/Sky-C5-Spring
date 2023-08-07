package com.qa.spring.hedgehog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No hedgehog found with that id")
public class HedgehogNotFoundException extends RuntimeException {

    public HedgehogNotFoundException() {
    }

    public HedgehogNotFoundException(String message) {
        super(message);
    }
}
