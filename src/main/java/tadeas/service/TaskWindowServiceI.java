package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.data.TaskWindowI;

import java.util.List;

@Service
@Lazy
public interface TaskWindowServiceI {

    public List<TaskWindowI> getWindows();

}
