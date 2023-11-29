package com.irfaan.restapi.controller;

import com.irfaan.restapi.exception.ApplicationException;
import com.irfaan.restapi.exception.AuthenticationException;
import com.irfaan.restapi.exception.ClientException;
import com.irfaan.restapi.exception.PathNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: DefaultExceptionHandler.java, v 0.1 2021‐11‐08 21.28 Ahmad Irfaan Hibatullah Exp $$
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private static final String STATUS = "status";

    @Autowired
    public DefaultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private ResponseEntity<Object> handleException(HttpStatus status) {
        String message;
        int value = status.value();
        if (value == 401) {
            message = "Maaf anda tidak tidak terautorisasi untuk mengakses ini";
        } else if (value == 400) {
            message = "Client bermasalah";
        } else if (value == 500) {
            message = "Internal Server Error";
        } else if (value == 403) {
            message = "Anda tidak terverifikasi";
        } else {
            message = "Server error";
        }
        return handleException(status, message);
    }

    private ResponseEntity<Object> handleException(HttpStatus status, String message) {
        Map<String, String> body = new HashMap<>();
        body.put(STATUS, "fail");
        body.put("msg", message);
        return ResponseEntity.status(status.value()).body(body);
    }

    private ResponseEntity<Object> handleExceptionFromMessageSource(HttpStatus status, String message) {
        String localizedMessage = messageSource.
                getMessage(message, null, message, LocaleContextHolder.getLocale());
        Map<String, String> body = new HashMap<>();
        body.put(STATUS, "fail");
        body.put("msg", localizedMessage);
        return ResponseEntity.status(status.value()).body(body);
    }

    private ResponseEntity<Object> handleBindingResult(BindingResult result, HttpStatus status) {
        Map<String, List<String>> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            String name = fieldError.getField();
            String value = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            List<String> messages = errors.computeIfAbsent(name, k -> new ArrayList<>());

            messages.add(value);
        });

        String message = messageSource.getMessage("error." + status.value(),
                null, LocaleContextHolder.getLocale());
        Map<String, String> body = new HashMap<>();
        body.put(STATUS, "fail");
        body.put("msg", message);
        return ResponseEntity.status(status.value()).body(body);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        logger.error("Application Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        logger.error("Missing Request Header Exception : ", ex);
        return handleException(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException ex) {
        logger.error("Client Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(PathNotFoundException.class)
    public ResponseEntity<Object> handlePathNotFoundException(PathNotFoundException ex) {
        logger.error("Path Not Found Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        logger.error("Authentication Exception : ", ex);
        return handleException(ex.getStatus(), ex.getReason());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception e) {
        logger.error("Unknown Exception : ", e);
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException e) {
        logger.error("Malformed JWT Exception : ", e);
        return handleException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        logger.error("BadCredentialsException : ", e);
        return handleException(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        logger.error("InternalAuthenticationServiceException : ", e);
        return handleException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        logger.error("UsernameNotFoundException : ", e);
        return handleException(HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex.getBindingResult(), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionFromMessageSource(status, ex.getMessage());
    }
}
