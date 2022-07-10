package com.twoimpulse.libraryflow.api.utils;

import com.twoimpulse.libraryflow.error.BusinessError;
import com.twoimpulse.libraryflow.error.RestError;
import com.twoimpulse.libraryflow.error.TechnicalError;
import com.twoimpulse.libraryflow.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestError handleBusinessRuleValidationError(HttpServletRequest request, HttpServletResponse response, Exception exception) {

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        if (exception instanceof BusinessException businessException) {
            return new BusinessError(businessException.getMessageKey(), businessException.getArguments());
        } else {
            return new TechnicalError(exception);
        }
    }
}
