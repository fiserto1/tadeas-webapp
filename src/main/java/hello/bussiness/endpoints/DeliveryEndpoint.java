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
    private Boolean valid;
    private Integer deliveryUser;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date acceptanceDate;
    private Integer acceptanceUser;
    private String acceptanceMessage;
    private String acceptanceBinary;
    private Boolean acceptation;


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

    public Boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getDeliveryUser() {
        return deliveryUser;
    }

    public void setDeliveryUser(int deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Integer getAcceptanceUser() {
        return acceptanceUser;
    }

    public void setAcceptanceUser(int acceptanceUser) {
        this.acceptanceUser = acceptanceUser;
    }

    public String getAcceptanceMessage() {
        return acceptanceMessage;
    }

    public void setAcceptanceMessage(String acceptanceMessage) {
        this.acceptanceMessage = acceptanceMessage;
    }

    public String getAcceptanceBinary() {
        return acceptanceBinary;
    }

    public void setAcceptanceBinary(String acceptanceBinary) {
        this.acceptanceBinary = acceptanceBinary;
    }

    public Boolean isAcceptation() {
        return acceptation;
    }

    public void setAcceptation(boolean acceptation) {
        this.acceptation = acceptation;
    }

    @Override
    public String toString() {
        return "DeliveryEndpoint{" +
                "id=" + id +
                ", taskDeliveryWindow=" + taskDeliveryWindow +
                ", solution='" + solution + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", valid=" + valid +
                ", deliveryUser=" + deliveryUser +
                ", acceptanceDate=" + acceptanceDate +
                ", acceptanceUser=" + acceptanceUser +
                ", acceptanceMessage='" + acceptanceMessage + '\'' +
                ", acceptanceBinary='" + acceptanceBinary + '\'' +
                ", acceptation=" + acceptation +
                '}';
    }
}
