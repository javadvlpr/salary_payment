package uz.abu.salary_payment.common;

public class AlreadyException extends  RuntimeException {
    public AlreadyException(String message) {
        super(message);
    }
}
