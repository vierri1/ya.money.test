package ya.money.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.*;

/**
 * Сущность задачи
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskEntity {

    /**
     * Идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Ключ (название) задачи.
     */
    @XmlElement
    private String key;
    /**
     * Резюме по задачи.
     */
    @XmlElement
    private String summary;
    /**
     * Описание задачи.
     */
    @XmlElement
    private String description;
    /**
     * Проект, к которому относится задача.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

}
