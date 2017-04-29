package com.pinmost.api.web.advice;

import com.pinmost.api.common.exception.CustomerException;
import com.pinmost.api.common.exception.NotFoundException;
import com.pinmost.api.common.exception.UnauthorizedException;
import com.pinmost.api.web.result.ErrorResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * User: mei
 * Date: 2/18/16
 * Time: 16:48
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Log LOG = LogFactory.getLog(ExceptionAdvice.class);
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handleNotFoundException(Exception exception) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getMessage());
        return errorResult;
    }


    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResult handleUnauthorizedException(Exception exception) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getMessage());
        return errorResult;
    }


    @ExceptionHandler({MissingServletRequestParameterException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handleParamException(Exception exception) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getMessage());
        return errorResult;
    }

    @ExceptionHandler({CustomerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handleCustomerException(Locale locale, CustomerException exception) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(messageSource.getMessage(exception.getErrorCode(), exception.getStubParams(), locale));
        return errorResult;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResult handleException(Exception exception) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getMessage());
        LOG.error(exception.getMessage(), exception);
        return errorResult;
    }
}
