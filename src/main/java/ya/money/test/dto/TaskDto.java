package ya.money.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для задачи
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    /**
     * Ключ проекта.
     */
    private String projectKey;
    /**
     * Ключ задачи.
     */
    private String taskKey;
    /**
     * Описание задачи.
     */
    private String description;
}
