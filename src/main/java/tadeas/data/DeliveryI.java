package tadeas.data;

import tadeas.dto.UserDTO;

import java.util.Date;

public interface DeliveryI {

    int getId();

    void setId(int id);

    String getSolution();

    void setSolution(String solution);

    Date getDeliveryDate();

    void setDeliveryDate(Date deliveryDate);

    Boolean isValid();

    void setValid(boolean valid);

    UserDTO getDeliveryUser();

    void setDeliveryUser(UserDTO deliveryUser);

    Date getAcceptanceDate();

    void setAcceptanceDate(Date acceptanceDate);

    UserDTO getAcceptanceUser();

    void setAcceptanceUser(UserDTO acceptanceUser);

    String getAcceptanceMessage();

    void setAcceptanceMessage(String acceptanceMessage);

    String getAcceptanceBinary();

    void setAcceptanceBinary(String acceptanceBinary);

    Boolean isAccepted();

    boolean isEvaluated();

}
