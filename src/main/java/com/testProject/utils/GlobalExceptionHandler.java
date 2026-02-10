package com.testProject.utils;

import com.testProject.dto.ResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(400);
        responseDTO.setMessage(Constants.Messages.badRequest);
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().stream().peek(er->{
            log.info(er.getField()+" : Empty or Blank");
        } ).forEach(es-> {
            errors.put("msg",es.getDefaultMessage());
            return;
        });

//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put("msg", error.getDefaultMessage())
//
//        );

        responseDTO.setResponseObj(errors);
        return ResponseEntity.badRequest().body(responseDTO);
    }
}
