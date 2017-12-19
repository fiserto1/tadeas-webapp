package hello.bussiness.models;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public interface TaskListI{

    public List<TaskWindowI> getWindows();

}
