package stg.template.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/2/3
 * @Todo:
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends BaseException {
    public InternalException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}