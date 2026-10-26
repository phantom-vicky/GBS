
package com.isl.gbm.rbibond.exceptions;
import com.isl.gbm.user.management.dto.ResponseDataDTO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.TerminatedRetryException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DisabledAccountException.class)
    public ResponseEntity<ResponseDataDTO> handleDisabledAccount(DisabledAccountException e) {
        ResponseDataDTO ResponseDataDTO=new ResponseDataDTO();
        ResponseDataDTO.setMessage("This account is disabled");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDataDTO);
    }

    @ExceptionHandler(LockedAccountException.class)
    public ResponseEntity<ResponseDataDTO> handleLockedAccount(LockedAccountException e) {
        ResponseDataDTO ResponseDataDTO=new ResponseDataDTO();
        ResponseDataDTO.setMessage("This account is Locked");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDataDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDataDTO> handleAuthenticationError(AuthenticationException e) {
        ResponseDataDTO ResponseDataDTO=new ResponseDataDTO();
        ResponseDataDTO.setMessage("Authentication Failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDataDTO);
    }
    @ExceptionHandler(AccountTerminatedException.class)
    public ResponseEntity<ResponseDataDTO> handleAccountTerminated(AccountTerminatedException e) {
        ResponseDataDTO response = new ResponseDataDTO();
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
