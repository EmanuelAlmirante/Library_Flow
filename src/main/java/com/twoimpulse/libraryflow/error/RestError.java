package com.twoimpulse.libraryflow.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public abstract class RestError implements Serializable {

    @JsonIgnore
    private Exception exception;

    protected RestError() {
    }

    public RestError(Exception exception) {
        this.exception = exception;
    }

    @JsonIgnore
    public abstract RuntimeException getException();
}
