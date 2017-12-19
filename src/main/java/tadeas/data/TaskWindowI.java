package tadeas.data;

import tadeas.dto.TaskDTO;

import java.util.Date;
import java.util.List;

public interface TaskWindowI {

    int getId();

    void setId(int id);

    TaskDTO getTask();

    void setTask(TaskDTO task);

    int getIssuer();

    void setIssuer(int issuer);

    boolean isActive();

    void setActive(boolean active);

    Date getIssueDate();

    void setIssueDate(Date issueDate);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getDeadlineDate();

    void setDeadlineDate(Date deadlineDate);

    String getDefinition();

    void setDefinition(String definition);

    List<DeliveryI> getDeliveries();

    void setDeliveries(List<DeliveryI> deliveries);

    DeliveryI getLastDelivery();

    boolean isAccepted();

}
