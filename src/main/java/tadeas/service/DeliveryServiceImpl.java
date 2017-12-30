package tadeas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tadeas.data.Delivery;
import tadeas.data.DeliveryI;
import tadeas.dto.DeliveryDTO;
import tadeas.form.EvaluationForm;
import tadeas.storage.StorageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service
@Primary
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TaskWindowService taskWindowService;

    @Value("${backend.url}")
    private String url;

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

    @Override
    public boolean evaluateDelivery(int deliveryId, EvaluationForm evaluation) {
        String deliveryUrl = url + "delivery/" + deliveryId;
        Map<String, Object> body = new HashMap<>();
        body.put("acceptanceDate", LocalDateTime.now());
        body.put("acceptanceMessage", evaluation.getAcceptanceMessage());
        body.put("acceptation", evaluation.getAcceptance());
        ResponseEntity<Object> noContent = restTemplate.postForEntity(deliveryUrl, body, Object.class);

        if (HttpStatus.NO_CONTENT.equals(noContent.getStatusCode())) {
            log.info("Delivery evaluated successfully.");
            return true;
        } else {
            log.info("Failed to evaluate delivery.");
            return false;
        }
    }

    @Override
    public Map<Integer, List<DeliveryI>> getDeliveries() {
        String deliveryUrl = url + "delivery";
        ResponseEntity<DeliveryDTO[]> responseDelivery = restTemplate.getForEntity(deliveryUrl, DeliveryDTO[].class);
        DeliveryDTO[] delivery = responseDelivery.getBody();
        Map<Integer, List<DeliveryI>> result = new HashMap<>();
        for (DeliveryDTO deli : delivery) {
            log.info(deli.toString());
            int windowsId = deli.getTaskDeliveryWindow().getId();
            if (result.containsKey(windowsId)) {
                result.get(windowsId).add(new Delivery(deli));
            } else {
                List<DeliveryI> delis = new ArrayList<>();
                delis.add(new Delivery(deli));
                result.put(windowsId, delis);
            }
        }
        return result;
    }

    @Override
    public Resource getDeliveryResource(int deliveryId) {
        final String filename = "file-" + deliveryId + ".txt";
        return storageService.loadAsResource(filename);
    }
}
