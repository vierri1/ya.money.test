package ya.money.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ya.money.test.entity.ProjectEntity;

import javax.xml.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectContainer {

    @XmlElement(name = "project")
    private List<ProjectEntity> projects;
}
