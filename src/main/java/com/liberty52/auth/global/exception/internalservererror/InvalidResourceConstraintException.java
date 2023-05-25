package com.liberty52.auth.global.exception.internalservererror;

import com.liberty52.auth.global.exception.internalservererror.InternalServerErrorException;

public class InvalidResourceConstraintException extends InternalServerErrorException {
    public InvalidResourceConstraintException(RuntimeException e) {
        super(e.getClass().getSimpleName());
    }
}
