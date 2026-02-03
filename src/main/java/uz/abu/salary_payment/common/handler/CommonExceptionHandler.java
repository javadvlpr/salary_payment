package uz.abu.salary_payment.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.abu.salary_payment.common.AlreadyException;
import uz.abu.salary_payment.common.AuthenticationException;
import uz.abu.salary_payment.common.DataNotFoundException;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public String authenticationFailure(AuthenticationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AlreadyException.class)
    public String alreadyException(AlreadyException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String dataNotFound(DataNotFoundException e) {
        return e.getMessage();
    }
}
