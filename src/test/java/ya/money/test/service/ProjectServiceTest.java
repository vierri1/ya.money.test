package ya.money.test.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import ya.money.test.entity.ProjectEntity;
import ya.money.test.entity.TaskEntity;
import ya.money.test.exception.ParseException;
import ya.money.test.exception.TaskNameConstraintException;
import ya.money.test.repository.ProjectRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static ya.money.test.data.ProjectTestData.PROJECTS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Captor
    private ArgumentCaptor<List<ProjectEntity>> projectsCapture;

    /**
     * Тестирование чтения корректного файла xml.
     */
    @Test
    public void readCorrectFileTest() throws IOException {

        projectService.saveProjects(getFile("correct.xml"));

        Mockito.verify(projectRepository, times(1)).saveAll(projectsCapture.capture());
        List<ProjectEntity> actual = projectsCapture.getValue();
        Assertions.assertThat(actual.size()).isEqualTo(PROJECTS.size());
        for (int i = 0; i < actual.size(); i++) {
            ProjectEntity actualProject = actual.get(i);
            ProjectEntity expectedProject = PROJECTS.get(i);
            Assertions.assertThat(actualProject)
                    .isEqualToIgnoringGivenFields(expectedProject, "tasks");
            List<TaskEntity> actualTasks = actualProject.getTasks();
            List<TaskEntity> expectedTasks = expectedProject.getTasks();

            Assertions.assertThat(actualTasks.size()).isEqualTo(expectedTasks.size());
            for (int j = 0; j < actualTasks.size(); j++) {
                Assertions.assertThat(actualTasks.get(j)).isEqualToComparingFieldByField(expectedTasks.get(j));
            }
        }
    }

    /**
     * Тестирование сохранения проектов с некорректным xml - отстутствует key проекта.
     *
     * @throws IOException
     */
    @Test(expected = ParseException.class)
    public void projectWithoutProjectKeyTest() throws IOException {
        projectService.saveProjects(getFile("incorrect.xml"));
    }

    /**
     * Тестирование сохранения проектов с некорректным xml - отстутствует key задачи.
     *
     * @throws IOException
     */
    @Test(expected = ParseException.class)
    public void projectWithoutTaskKeyTest() throws IOException {
        projectService.saveProjects(getFile("incorrect2.xml"));
    }

    /**
     * Тестирование сохранения проектов с некорректным xml - отстутствует тег tasks.
     *
     * @throws IOException
     */
    @Test(expected = ParseException.class)
    public void projectWithoutTaskTagTest() throws IOException {
        projectService.saveProjects(getFile("incorrect3.xml"));
    }

    /**
     * Тестирование сохранения проектов с некорректным xml - название задачи начинается не с ключа проекта.
     *
     * @throws IOException
     */
    @Test(expected = TaskNameConstraintException.class)
    public void wrongTaskNameTest() throws IOException {
        projectService.saveProjects(getFile("incorrect4.xml"));
    }

    /**
     * Создает заглушку в для multipartFIle
     *
     * @param path путь к файлу.
     * @return заглушку MultipartFile из реального xml файла.
     * @throws IOException
     */
    private MultipartFile getFile(String path) throws IOException {
        File xml = ResourceUtils.getFile("classpath:" + path);
        byte[] content = Files.readAllBytes(xml.toPath());
        return new MockMultipartFile("file", content);
    }
}