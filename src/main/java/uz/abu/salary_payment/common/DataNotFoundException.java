package uz.abu.salary_payment.common;

public class DataNotFoundException extends  RuntimeException{
    public DataNotFoundException(String message) {
        super(message);
    }
}
