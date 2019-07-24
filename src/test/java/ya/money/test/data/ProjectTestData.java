package ya.money.test.data;

import ya.money.test.entity.ProjectEntity;
import ya.money.test.entity.TaskEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тестовые данные для проектов
 */
public class ProjectTestData {
    public static final List<ProjectEntity> PROJECTS;

    static {
        PROJECTS = new ArrayList<>();
        PROJECTS.add(new ProjectEntity(
                null,
                "BACK",
                "Backend project",
                Arrays.asList(
                        new TaskEntity(null, "BACK-1", "First task", "Task description", null),
                        new TaskEntity(null, "BACK-2", "2 task", "Task description 2", null),
                        new TaskEntity(null, "BACK-3", "3 task", "Task description 3", null))));
        PROJECTS.add(new ProjectEntity(
                null,
                "BACK2",
                "Backend project2",
                Arrays.asList(
                        new TaskEntity(null, "BACK2-1", "2 task", "Task description 21", null),
                        new TaskEntity(null, "BACK2-2", "task2", "Task description 22", null),
                        new TaskEntity(null, "BACK2-3", "task3", "Task description 23", null))));
    }
}
