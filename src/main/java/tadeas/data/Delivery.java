package tadeas.data;

import tadeas.dto.DeliveryDTO;
import tadeas.dto.UserDTO;

import java.io.Serializable;
import java.time.LocalDate;

public class Delivery implements DeliveryI, Serializable {

    private static final long serialVersionUID = -7539016443804822699L;

    private int id;
    private String solution;
    private LocalDate deliveryDate;
    private Boolean valid;
    private UserDTO deliveryUser;
    private LocalDate acceptanceDate;
    private UserDTO acceptanceUser;
    private String acceptanceMessage;
    private String acceptanceBinary;
    private Boolean acceptation;

    public Delivery(DeliveryDTO endpoint, UserDTO deliveryUser, UserDTO acceptanceUser) {
        this.id = endpoint.getId();
        this.solution = endpoint.getSolution();
        this.deliveryDate = endpoint.getDeliveryDate();
        this.valid = endpoint.isValid();
        this.acceptanceDate = endpoint.getAcceptanceDate();
        this.deliveryUser = deliveryUser;
        this.acceptanceMessage = endpoint.getAcceptanceMessage();
        this.acceptanceBinary = endpoint.getAcceptanceBinary();
        this.acceptanceUser = acceptanceUser;
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

    public UserDTO getDeliveryUser() {
        return deliveryUser;
    }

    public void setDeliveryUser(UserDTO deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDate acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public UserDTO getAcceptanceUser() {
        return acceptanceUser;
    }

    public void setAcceptanceUser(UserDTO acceptanceUser) {
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
