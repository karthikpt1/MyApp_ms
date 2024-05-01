package karthikpt.users.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistsException extends RuntimeException{

    public UserExistsException(String userName){
        super(String.format("%s already exists", userName));
    }

}
