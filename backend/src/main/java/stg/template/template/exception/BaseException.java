package stg.template.template.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public BaseException(HttpStatus code, String message) {
        super(message);
        this.code = code.value();
        this.message = message;
    }
}