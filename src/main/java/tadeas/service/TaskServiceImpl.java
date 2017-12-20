package tadeas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tadeas.dto.TaskDTO;

@Lazy
@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.url}")
    private String url;

    @Override
    public TaskDTO getTask(int taskId) {
        final String taskUrl = url + "task/" + taskId;
        ResponseEntity<TaskDTO> responseEntity = restTemplate.getForEntity(taskUrl, TaskDTO.class);
        return responseEntity.getBody();
    }
}
