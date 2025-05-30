package aiss.bitbucketminer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class ProjectBadRequestException extends Exception {
}
