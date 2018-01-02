package tadeas.data;

import tadeas.dto.TaskDTO;

import java.time.LocalDate;
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

    LocalDate getIssueDate();

    void setIssueDate(LocalDate issueDate);

    LocalDate getStartDate();

    void setStartDate(LocalDate startDate);

    LocalDate getDeadlineDate();

    void setDeadlineDate(LocalDate deadlineDate);

    String getDefinition();

    void setDefinition(String definition);

    List<DeliveryI> getDeliveries();

    void setDeliveries(List<DeliveryI> deliveries);

    DeliveryI getLastDelivery();

    boolean isAccepted();

}
