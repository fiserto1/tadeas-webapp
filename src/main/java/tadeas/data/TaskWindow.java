package tadeas.data;

import tadeas.dto.DeliveryWindowDTO;
import tadeas.dto.TaskDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskWindow implements TaskWindowI{
    private int id;
    private TaskDTO task;
    private int issuer;
    private boolean active;
    private Date issueDate;
    private Date startDate;
    private Date deadlineDate;
    private String definition;
    private List<DeliveryI> deliveries;


    public TaskWindow(DeliveryWindowDTO endpoint){
        this.id = endpoint.getId();
        this.task = endpoint.getTask();
        this.issuer = endpoint.getIssuer();
        this.active = endpoint.isActive();
        this.issueDate = endpoint.getIssueDate();
        this.startDate = endpoint.getStartDate();
        this.deadlineDate = endpoint.getDeadlineDate();
        this.definition = endpoint.getDefinition();
        this.deliveries = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public int getIssuer() {
        return issuer;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<DeliveryI> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryI> deliveries) {
        this.deliveries = deliveries;
    }

    public DeliveryI getLastDelivery(){
       return deliveries.get(this.deliveries.size() - 1);
    }

    public boolean isAccepted(){
        return getLastDelivery().isAccepted();
    }

    @Override
    public String toString() {
        return "TaskWindow{" +
                "id=" + id +
                ", task=" + task +
                ", issuer=" + issuer +
                ", active=" + active +
                ", issueDate=" + issueDate +
                ", startDate=" + startDate +
                ", deadlineDate=" + deadlineDate +
                ", definition='" + definition + '\'' +
                ", deliveries=" + deliveries +
                '}';
    }
}