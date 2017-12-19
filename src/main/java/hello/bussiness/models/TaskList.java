package hello.bussiness.models;

import hello.bussiness.endpoints.DeliveryEndpoint;
import hello.bussiness.endpoints.DeliveryWindowEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@RequestScope
public class TaskList  implements TaskListI {

    private static final Logger log = LoggerFactory.getLogger(TaskList.class);


    @Autowired
    private BeanFactory beanFactory;

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

    public TaskList(){
    }


    private List<TaskWindowI> scrapeWindows(){
        String windowsUrl = url + "windows";
        ResponseEntity<DeliveryWindowEndpoint[]> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowEndpoint[].class);
        DeliveryWindowEndpoint[] windows = responseEntity.getBody();
        List<TaskWindowI> result = new ArrayList<>();
        for (DeliveryWindowEndpoint win: windows){
            log.info(win.toString());
            result.add(new TaskWindow(win));
        }
        return result;
    }


    private Map<Integer, List<DeliveryI>> scrapeDelivery(){
        String deliveryUrl = url + "delivery";
        ResponseEntity<DeliveryEndpoint[]> responseDelivery = restTemplate.getForEntity(deliveryUrl, DeliveryEndpoint[].class);
        DeliveryEndpoint[] delivery = responseDelivery.getBody();
        Map<Integer, List<DeliveryI>> result = new HashMap<>();
        for (DeliveryEndpoint deli: delivery){
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
