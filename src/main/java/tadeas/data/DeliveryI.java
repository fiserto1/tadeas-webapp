package tadeas.data;

import tadeas.dto.UserDTO;

import java.time.LocalDate;

public interface DeliveryI {

    int getId();

    void setId(int id);

    String getSolution();

    void setSolution(String solution);

    LocalDate getDeliveryDate();

    void setDeliveryDate(LocalDate deliveryDate);

    Boolean isValid();

    void setValid(boolean valid);

    UserDTO getDeliveryUser();

    void setDeliveryUser(UserDTO deliveryUser);

    LocalDate getAcceptanceDate();

    void setAcceptanceDate(LocalDate acceptanceDate);

    UserDTO getAcceptanceUser();

    void setAcceptanceUser(UserDTO acceptanceUser);

    String getAcceptanceMessage();

    void setAcceptanceMessage(String acceptanceMessage);

    String getAcceptanceBinary();

    void setAcceptanceBinary(String acceptanceBinary);

    Boolean isAccepted();

    boolean isEvaluated();

}
