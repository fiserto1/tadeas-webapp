package hello.bussiness.endpoints;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryEndpoint {

    private int id;
    private DeliveryWindowEndpoint taskDeliveryWindow;
    private String solution;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date deliveryDate;
    private boolean valid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryWindowEndpoint getTaskDeliveryWindow() {
        return taskDeliveryWindow;
    }

    public void setTaskDeliveryWindow(DeliveryWindowEndpoint taskDeliveryWindow) {
        this.taskDeliveryWindow = taskDeliveryWindow;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", taskDeliveryWindow=" + taskDeliveryWindow +
                ", solution='" + solution + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", valid=" + valid +
                '}';
    }
}
