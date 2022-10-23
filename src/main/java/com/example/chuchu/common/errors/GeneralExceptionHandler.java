package com.example.chuchu.common.errors;

import com.example.chuchu.common.errors.exception.*;
import com.example.chuchu.common.global.HttpResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.chuchu.common.global.HttpResponseEntity.error;


@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    private ResponseEntity<HttpResponseEntity.ResponseResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        return newResponse(throwable.getMessage(), status);
    }

    private ResponseEntity<HttpResponseEntity.ResponseResult<?>> newResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(message, status), headers, status);
    }

    @ExceptionHandler({NotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(Exception e) {
        return newResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(Exception e) {
        return newResponse(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleConflictException(Exception e) {
        return newResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

}
