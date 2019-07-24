package ya.money.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ya.money.test.entity.TaskEntity;

/**
 * Репозиторий для хранения задачи.
 */
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
