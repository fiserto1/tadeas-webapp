package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.dto.TaskDTO;

@Lazy
@Service
public interface TaskService {
    TaskDTO getTask(int taskId);
}
