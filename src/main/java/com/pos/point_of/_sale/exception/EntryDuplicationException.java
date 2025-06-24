package com.pos.point_of._sale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntryDuplicationException extends RuntimeException {
    public EntryDuplicationException(String message) {
        super(message);
    }
}
