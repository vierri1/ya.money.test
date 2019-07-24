package ya.money.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ya.money.test.dto.TaskDto;
import ya.money.test.entity.ProjectEntity;
import ya.money.test.dto.ProjectContainer;
import ya.money.test.exception.ParseException;
import ya.money.test.repository.ProjectRepository;
import ya.money.test.validation.TaskNameValidator;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Логика по работе с загружаемыми проектами
 */
@Validated
@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Сохраняет список проектов с задачами.
     *
     * @param file файл со списком проектов в формате xml.
     */
    public void saveProjects(MultipartFile file) {
        List<ProjectEntity> projectEntities = parseProjects(file);
        TaskNameValidator.validateTaskNames(projectEntities);
        if (!projectEntities.isEmpty()) {
            projectRepository.saveAll(projectEntities);
        }
    }

    /**
     * Возвращает краткую информацию по всем задачам.
     *
     * @return Список задач.
     */
    public List<TaskDto> getTasks() {
        return projectRepository.getAllTasks();
    }

    /**
     * Осуществляет чтение xml файла.
     * Файл валидируется xsd схемой, а также проверяется валидность ключей задач.
     *
     * @param file входной xml файл.
     * @return список сущностей, считанных из входного файла.
     */
    private List<ProjectEntity> parseProjects(MultipartFile file) {
        try {
            File xsd = ResourceUtils.getFile("classpath:projects.xsd");
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsd);
            JAXBContext jaxbContext = JAXBContext.newInstance(ProjectContainer.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            ProjectContainer projectContainer = (ProjectContainer) unmarshaller.unmarshal(file.getInputStream());
            return Optional.ofNullable(projectContainer.getProjects())
                    .orElse(Collections.emptyList());
        } catch (JAXBException | IOException | SAXException e) {
            log.error("Ошибка парсинга xml файла: {}", e.getMessage());
            throw new ParseException(e.getCause());
        }
    }
}
