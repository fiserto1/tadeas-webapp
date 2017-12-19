package tadeas.data;

import java.util.Date;

public interface DeliveryI {

    public int getId() ;

    public void setId(int id);

    public String getSolution();

    public void setSolution(String solution);

    public Date getDeliveryDate();

    public void setDeliveryDate(Date deliveryDate);

    public Boolean isValid();

    public void setValid(boolean valid);

    public Integer getDeliveryUser();

    public void setDeliveryUser(int deliveryUser);

    public Date getAcceptanceDate();

    public void setAcceptanceDate(Date acceptanceDate);

    public Integer getAcceptanceUser();

    public void setAcceptanceUser(int acceptanceUser) ;

    public String getAcceptanceMessage() ;

    public void setAcceptanceMessage(String acceptanceMessage) ;

    public String getAcceptanceBinary() ;

    public void setAcceptanceBinary(String acceptanceBinary) ;

    public Boolean isAccepted();

    public boolean isEvaluated();

}
