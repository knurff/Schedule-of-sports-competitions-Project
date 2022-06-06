package com.example.sport_schedule.exceptions.handlers;

import com.example.sport_schedule.exceptions.custom.GameNotFoundException;
import com.example.sport_schedule.exceptions.custom.TeamNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)
    ResponseEntity<?> gameNotFoundHandler(GameNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    ResponseEntity<?> teamNotFoundHandler(TeamNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class})
    ResponseEntity<?> globalHandler(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
