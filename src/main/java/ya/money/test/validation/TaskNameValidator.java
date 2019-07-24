package ya.money.test.validation;

import ya.money.test.entity.ProjectEntity;
import ya.money.test.entity.TaskEntity;
import ya.money.test.exception.TaskNameConstraintException;

import java.util.List;
import java.util.Objects;

/**
 * Валидирует структуру задачи.
 */
public class TaskNameValidator {

    private TaskNameValidator() {
    }

    /**
     * Осуществляет проверку названий всех задач во всех проектах.
     *
     * @param projects список проектов.
     */
    public static void validateTaskNames(List<ProjectEntity> projects) {
        for (ProjectEntity project : projects) {
            if (Objects.nonNull(project)) {
                String projectKey = project.getKey();
                for (TaskEntity task : project.getTasks()) {
                    if (Objects.nonNull(task) && !taskHasProjectPrefix(projectKey, task.getKey())) {
                        throw new TaskNameConstraintException();
                    }
                }
            }
        }
    }

    /**
     * Название задачи должно начинаться с названия проекта.
     *
     * @param projectName название проекта.
     * @param taskName    название задачи.
     * @return true - название задачи начинается с названия проекта, false -  не начинается.
     */
    private static boolean taskHasProjectPrefix(String projectName, String taskName) {
        return taskName.startsWith(projectName);
    }

}
