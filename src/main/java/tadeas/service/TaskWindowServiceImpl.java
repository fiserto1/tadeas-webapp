package tadeas.service;

import org.springframework.context.annotation.Primary;
import tadeas.dto.DeliveryWindowDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import tadeas.data.DeliveryI;
import tadeas.data.TaskWindow;
import tadeas.data.TaskWindowI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Lazy
@Primary
@Service
@RequestScope
public class TaskWindowServiceImpl implements TaskWindowService {

    private static final Logger log = LoggerFactory.getLogger(TaskWindowServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeliveryService deliveryService;

    @Value("${backend.url}")
    private String url;

    public List<TaskWindowI> getTaskWindows() {
        Map<Integer, List<DeliveryI>> deliveries = deliveryService.getDeliveries();
        List<TaskWindowI> windows = scrapeWindows();
        for (TaskWindowI task : windows) {
            if (deliveries.containsKey(task.getId())) {
                task.setDeliveries(deliveries.get(task.getId()));
            }
        }
        return windows;
    }

    @Override
    public TaskWindow getTaskWindow(int taskId) {
        String windowsUrl = url + "window/" + taskId;
        ResponseEntity<DeliveryWindowDTO> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowDTO.class);

        DeliveryWindowDTO window = responseEntity.getBody();

        if (window == null) {
            throw new IllegalArgumentException("Task window not found.");
        }

        if (isAfterDeadline(window)) {
            window.setActive(false);
        }

        return new TaskWindow(window);
    }

    private boolean isAfterDeadline(DeliveryWindowDTO window) {
        return LocalDate.now().isAfter(window.getDeadlineDate());
    }

    private List<TaskWindowI> scrapeWindows() {
        String windowsUrl = url + "window";
        ResponseEntity<DeliveryWindowDTO[]> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowDTO[].class);
        DeliveryWindowDTO[] windows = responseEntity.getBody();
        List<TaskWindowI> result = new ArrayList<>();
        for (DeliveryWindowDTO win : windows) {
            if (isAfterDeadline(win)) {
                win.setActive(false);
            }
            log.info(win.toString());
            result.add(new TaskWindow(win));
        }
        return result;
    }


}
