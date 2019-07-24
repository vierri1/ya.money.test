package ya.money.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Сущность проекта
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectEntity {

    /**
     * Идентификатор проекта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Ключ (название) проекта
     */
    @XmlElement
    private String key;
    /**
     * Резюме по проекту.
     */
    @XmlElement
    private String summary;
    /**
     * Список задач проекта.
     */
    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<TaskEntity> tasks;
}


