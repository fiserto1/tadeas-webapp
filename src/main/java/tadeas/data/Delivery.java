package tadeas.data;

import tadeas.dto.DeliveryDTO;

import java.io.Serializable;
import java.util.Date;

public class Delivery implements DeliveryI, Serializable {

    private static final long serialVersionUID = -7539016443804822699L;

    private int id;
    private String solution;
    private Date deliveryDate;
    private Boolean valid;
    private Integer deliveryUser;
    private Date acceptanceDate;
    private Integer acceptanceUser;
    private String acceptanceMessage;
    private String acceptanceBinary;
    private Boolean acceptation;

    public Delivery(DeliveryDTO endpoint) {
        this.id = endpoint.getId();
        this.solution = endpoint.getSolution();
        this.deliveryDate = endpoint.getDeliveryDate();
        this.valid = endpoint.isValid();
        this.deliveryUser = endpoint.getDeliveryUser();
        this.acceptanceDate = endpoint.getAcceptanceDate();
        this.deliveryUser = endpoint.getDeliveryUser();
        this.acceptanceMessage = endpoint.getAcceptanceMessage();
        this.acceptanceBinary = endpoint.getAcceptanceBinary();
        this.acceptanceUser = endpoint.getAcceptanceUser();
        this.acceptation = endpoint.isAcceptation();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean isAccepted() {
        return acceptation;
    }

    @Override
    public boolean isEvaluated() {
        return acceptation != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        return id == delivery.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
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
