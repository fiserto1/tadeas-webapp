package hello.bussiness.models;

import java.util.Date;

public interface DeliveryI {

    public int getId() ;

    public void setId(int id);

    public String getSolution();

    public void setSolution(String solution);

    public Date getDeliveryDate();

    public void setDeliveryDate(Date deliveryDate);

    public boolean isValid();

    public void setValid(boolean valid);

    public int getDeliveryUser();

    public void setDeliveryUser(int deliveryUser);

    public Date getAcceptanceDate();

    public void setAcceptanceDate(Date acceptanceDate);

    public int getAcceptanceUser();

    public void setAcceptanceUser(int acceptanceUser) ;

    public String getAcceptanceMessage() ;

    public void setAcceptanceMessage(String acceptanceMessage) ;

    public String getAcceptanceBinary() ;

    public void setAcceptanceBinary(String acceptanceBinary) ;

    public boolean isAccepted();

    public boolean isEvaluated();

}
