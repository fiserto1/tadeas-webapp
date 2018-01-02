package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.data.TaskWindow;
import tadeas.data.TaskWindowI;

import java.util.List;

@Lazy
@Service
public interface TaskWindowService {

    List<TaskWindowI> getTaskWindows();

    TaskWindow getTaskWindow(int taskId);
}
