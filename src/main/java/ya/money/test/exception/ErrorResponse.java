package ya.money.test.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Структура общего ответа об ошибке
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String errorMessage;
    private String details;
    private String path;

    public ErrorResponse(String errorMessage, String path) {
        this.errorMessage = errorMessage;
        this.path = path;
    }

}
