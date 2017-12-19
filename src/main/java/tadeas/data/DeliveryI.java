package tadeas.data;

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

    Integer getDeliveryUser();

    void setDeliveryUser(int deliveryUser);

    Date getAcceptanceDate();

    void setAcceptanceDate(Date acceptanceDate);

    Integer getAcceptanceUser();

    void setAcceptanceUser(int acceptanceUser);

    String getAcceptanceMessage();

    void setAcceptanceMessage(String acceptanceMessage);

    String getAcceptanceBinary();

    void setAcceptanceBinary(String acceptanceBinary);

    Boolean isAccepted();

    boolean isEvaluated();

}
