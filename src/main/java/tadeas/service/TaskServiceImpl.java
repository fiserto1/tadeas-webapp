package tadeas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tadeas.data.Task;
import tadeas.data.TaskWindow;
import tadeas.dto.TaskDTO;
import tadeas.dto.UserDTO;

@Lazy
@Primary
@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskWindowService taskWindowService;

    @Value("${backend.url}")
    private String url;

    @Override
    public Task getTask(int taskId) {
        final String taskUrl = url + "task/" + taskId;
        ResponseEntity<TaskDTO> responseEntity = restTemplate.getForEntity(taskUrl, TaskDTO.class);
        TaskDTO taskDTO = responseEntity.getBody();

        if (taskDTO == null) {
            throw new IllegalArgumentException("Task not found");
        }

        TaskWindow taskWindow = taskWindowService.getTaskWindow(taskId);

        if (taskWindow == null) {
            throw new IllegalArgumentException("Task window not found.");
        }

        taskDTO.setActive(taskWindow.isActive());

        UserDTO issuer = userService.getUser(taskDTO.getIssuer());
        return new Task(taskDTO, issuer);
    }
}
