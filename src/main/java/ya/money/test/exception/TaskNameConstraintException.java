package ya.money.test.exception;

/**
 * Ошибка, выбрасываемая при нарушении ограничения по названию задачи.
 */
public class TaskNameConstraintException extends RuntimeException {

    public TaskNameConstraintException() {
        super("Ключи задач должны начинаться с ключа проекта");
    }
}
