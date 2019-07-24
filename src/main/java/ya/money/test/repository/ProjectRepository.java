package ya.money.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ya.money.test.dto.TaskDto;
import ya.money.test.entity.ProjectEntity;

import java.util.List;

/**
 * Репозиторий для хранения проектов.
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("select new ya.money.test.dto.TaskDto(project.key, task.key, task.description) " +
            "from ProjectEntity project inner join project.tasks task")
    List<TaskDto> getAllTasks();

}
