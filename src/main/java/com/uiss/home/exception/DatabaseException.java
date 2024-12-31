package com.uiss.home.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class DatabaseException extends RuntimeException {
    private final String errorCode;
}
