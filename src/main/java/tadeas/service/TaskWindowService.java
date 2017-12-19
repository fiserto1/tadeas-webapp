package tadeas.service;

import org.springframework.http.HttpStatus;
import tadeas.dto.DeliveryDTO;
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
import tadeas.data.Delivery;
import tadeas.data.DeliveryI;
import tadeas.data.TaskWindow;
import tadeas.data.TaskWindowI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@RequestScope
public class TaskWindowService implements TaskWindowServiceI {

    private static final Logger log = LoggerFactory.getLogger(TaskWindowService.class);

    private List<TaskWindowI> windows;

    @Autowired
    RestTemplate restTemplate;

    @Value("${backend.url}")
    private String url;

    public List<TaskWindowI> getWindows() {
        Map<Integer, List<DeliveryI>> deliveries = this.scrapeDelivery();
        this.windows = scrapeWindows();
        for (TaskWindowI task: this.windows){
            if (deliveries.containsKey(task.getId())){
                task.setDeliveries(deliveries.get(task.getId()));
            }
        }
        return windows;
    }

    @Override
    public boolean confirmDelivery(int deliveryId) {
        String deliveryUrl = url + "delivery/" + deliveryId;
        Map<String, Boolean> body = new HashMap<>();
        body.put("valid", true);
        ResponseEntity<Object> noContent = restTemplate.postForEntity(deliveryUrl, body, Object.class);

        if (HttpStatus.NO_CONTENT.equals(noContent.getStatusCode())) {
            log.info("Delivery confirmed successfully.");
            return true;
        } else {
            log.info("Failed to confirm delivery.");
            return false;
        }
    }

    public TaskWindowService(){
    }


    private List<TaskWindowI> scrapeWindows(){
        String windowsUrl = url + "windows";
        ResponseEntity<DeliveryWindowDTO[]> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowDTO[].class);
        DeliveryWindowDTO[] windows = responseEntity.getBody();
        List<TaskWindowI> result = new ArrayList<>();
        for (DeliveryWindowDTO win: windows){
            log.info(win.toString());
            result.add(new TaskWindow(win));
        }
        return result;
    }


    private Map<Integer, List<DeliveryI>> scrapeDelivery(){
        String deliveryUrl = url + "delivery";
        ResponseEntity<DeliveryDTO[]> responseDelivery = restTemplate.getForEntity(deliveryUrl, DeliveryDTO[].class);
        DeliveryDTO[] delivery = responseDelivery.getBody();
        Map<Integer, List<DeliveryI>> result = new HashMap<>();
        for (DeliveryDTO deli: delivery){
            log.info(deli.toString());
            int windowsId = deli.getTaskDeliveryWindow().getId();
            if (result.containsKey(windowsId)){
                result.get(windowsId).add(new Delivery(deli));
            }
            else {
                List<DeliveryI> delis = new ArrayList<>();
                delis.add(new Delivery(deli));
                result.put(windowsId, delis);
            }
        }
        return result;
    }



}
