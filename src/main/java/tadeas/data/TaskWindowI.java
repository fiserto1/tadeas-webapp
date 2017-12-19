package tadeas.data;

import tadeas.dto.TaskDTO;

import java.util.Date;
import java.util.List;

public interface TaskWindowI {

    public int getId();

    public void setId(int id);

    public TaskDTO getTask();

    public void setTask(TaskDTO task);

    public int getIssuer();

    public void setIssuer(int issuer);

    public boolean isActive();

    public void setActive(boolean active);

    public Date getIssueDate();

    public void setIssueDate(Date issueDate);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public Date getDeadlineDate();

    public void setDeadlineDate(Date deadlineDate);

    public String getDefinition();

    public void setDefinition(String definition);

    public List<DeliveryI> getDeliveries();

    public void setDeliveries(List<DeliveryI> deliveries);

    public DeliveryI getLastDelivery();

    public boolean isAccepted();

}