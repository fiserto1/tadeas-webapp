package tadeas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDTO implements Serializable {
    private static final long serialVersionUID = 2644572024896694526L;

    private int id;
    private DeliveryWindowDTO taskDeliveryWindow;
    private String solution;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate deliveryDate;
    private Boolean valid;
    private Integer deliveryUser;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate acceptanceDate;
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

    public DeliveryWindowDTO getTaskDeliveryWindow() {
        return taskDeliveryWindow;
    }

    public void setTaskDeliveryWindow(DeliveryWindowDTO taskDeliveryWindow) {
        this.taskDeliveryWindow = taskDeliveryWindow;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
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

    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDate acceptanceDate) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryDTO that = (DeliveryDTO) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
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
