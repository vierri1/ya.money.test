package ya.money.test.exception;

/**
 * Ошибка разбора xml файла.
 */
public class ParseException extends RuntimeException {

    public ParseException(Throwable cause) {
        super("Ошибка парсинга xml файла", cause);
    }
}
