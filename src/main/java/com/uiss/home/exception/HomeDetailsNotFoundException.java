package com.uiss.home.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class HomeDetailsNotFoundException extends RuntimeException {
    private final String errorCode;
}
