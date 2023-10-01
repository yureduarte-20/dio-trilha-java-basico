package dio.me.desafiofinal.santander.application.controllers.exceptions;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.GivenDateInvalidException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.application.services.exceptions.dto.GivenDateInvalidExceptionDTO;
import dio.me.desafiofinal.santander.application.services.exceptions.dto.NotFoundExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.AlreadyBoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(AlreadyExistsException.class)
    ResponseEntity<String> handleBusinessException(AlreadyExistsException businessException){
        return new ResponseEntity<>(businessException.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<NotFoundExceptionDTO> handleBusinessException(NotFoundException businessException){
        return new ResponseEntity<>(
                new NotFoundExceptionDTO(businessException.getModel(),
                businessException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GivenDateInvalidException.class)
    ResponseEntity<GivenDateInvalidExceptionDTO>handleBusinessException(GivenDateInvalidException businessException){
        return new ResponseEntity<>(new GivenDateInvalidExceptionDTO(businessException.getModel(), businessException.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(Throwable.class)
    ResponseEntity<String> handleBusinessException(Throwable unespectedThrowable){
        var message = "{\"message\":\"Erro inesperado!\"}";
        logger.error(message, unespectedThrowable);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
