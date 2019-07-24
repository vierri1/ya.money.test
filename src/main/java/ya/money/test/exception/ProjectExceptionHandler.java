package ya.money.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Перехватывание и обработка ошибок.
 */
@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка ошибок при парсинге файла.
     *
     * @param ex      объект ошибки парсинга.
     * @param request объект запроса
     * @return DTO с необходимой информацией об ошибке.
     */
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponse> parseExceptionHandler(ParseException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        ex.getCause().getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработка ошибок валидации ограничения названия задачи.
     *
     * @param ex      объект ошибки ограничения.
     * @param request объект запроса
     * @return DTO с необходимой информацией об ошибке.
     */
    @ExceptionHandler(TaskNameConstraintException.class)
    public ResponseEntity<ErrorResponse> taskNameConstraintException(TaskNameConstraintException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработка непредвиденных ошибок.
     *
     * @param ex      объект ошибки.
     * @param request объект запроса.
     * @return DTO с необходимой информацией об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        "Внутренняя ошибка сервера",
                        request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
