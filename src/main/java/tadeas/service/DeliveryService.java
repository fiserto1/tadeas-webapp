package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tadeas.data.DeliveryI;
import tadeas.form.EvaluationForm;

import java.util.List;
import java.util.Map;

@Lazy
@Service
public interface DeliveryService {

    boolean confirmDelivery(int taskId);

    boolean evaluateDelivery(int deliveryId, EvaluationForm evaluation);

    Map<Integer, List<DeliveryI>> getDeliveries();

    Resource getDeliveryResource(int deliveryId);
}
