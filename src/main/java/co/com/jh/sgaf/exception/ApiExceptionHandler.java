package co.com.jh.sgaf.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.com.jh.sgaf.common.StandarizedApiExceptionResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase Manejadora de las excepciones de la API. Se especializa basicamente en manejar cualquier excepcion que 
 * lanze la Api. Esta clase asiste a una clase de controlador y puede tener un cuerpo en la respuesta.
 * Standard http communication have five levels of response codes 
 * 100-level (Informational) — Server acknowledges a request, it mean that request was received and understood, it
 * is transient response , alert client for awaiting response 
 * 200-level (Success) — Server completed the request as expected 
 * 300-level (Redirection) — Client needs to perform further actions to complete the request 
 * 400-level (Client error) — Client sent an invalid request 
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 *
 * The goal of handler exception is provide to customer with appropriate code and additional comprehensible 
 * information to help troubleshoot the problem. The message portion of the body should be present as user 
 * interface, event if customer send an Accept-Language header (en or french ie) we should translate the title part
 * to customer language if we support internationalization, detail is intended for developer of clients, so the
 * translation is not necessary. If more than one error is need to report , we can response a list of errors.
 * 
 * @author jsherreram
 * @version 1.0
 */
@RestControllerAdvice // Indicate that this class assit a controller class and can have a body in response.
public class ApiExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleNoContentException(IOException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de entrada y salida", "Error-1023", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de conexión", "Error-1024", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

    // Allow define a method for handler this particular exception in transversal way, as a global exception handler.
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBussinesRuleException(BusinessRuleException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validación", ex.getCode(), ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

}
