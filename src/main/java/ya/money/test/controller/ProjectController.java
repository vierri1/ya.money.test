package ya.money.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ya.money.test.service.ProjectService;

/**
 * Контроллер для работы с проектами.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Возвращает страницу с загрузкой проектов.
     *
     * @return html страницу загрузки.
     */
    @GetMapping
    public String uploadPage() {
        return "upload_page";
    }

    /**
     * Загрузка xml файла с проектами.
     *
     * @param file загружаемый файл.
     * @return редирект на страницу загрузки.
     */
    @PostMapping
    public String upload(@RequestPart MultipartFile file) {
        projectService.saveProjects(file);
        return "redirect:/project";
    }

    /**
     * Возвращает страницу со списком задач.
     *
     * @param model модель запроса.
     * @return html страницу со списком задач.
     */
    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", projectService.getTasks());
        return "tasks";
    }
}
