package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.data.Task;

@Lazy
@Service
public interface TaskService {
    Task getTask(int taskId);
}
