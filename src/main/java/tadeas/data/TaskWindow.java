package tadeas.data;

import tadeas.dto.DeliveryWindowDTO;
import tadeas.dto.TaskDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskWindow implements TaskWindowI, Serializable {
    private static final long serialVersionUID = 5885065083351165836L;

    private int id;
    private TaskDTO task;
    private int issuer;
    private boolean active;
    private LocalDate issueDate;
    private LocalDate startDate;
    private LocalDate deadlineDate;
    private String definition;
    private List<DeliveryI> deliveries;

    public TaskWindow(DeliveryWindowDTO windowDTO) {
        this.id = windowDTO.getId();
        this.task = windowDTO.getTask();
        this.issuer = windowDTO.getIssuer();
        this.active = windowDTO.isActive();
        this.issueDate = windowDTO.getIssueDate();
        this.startDate = windowDTO.getStartDate();
        this.deadlineDate = windowDTO.getDeadlineDate();
        this.definition = windowDTO.getDefinition();
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
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

    public DeliveryI getLastDelivery() {
        return deliveries.get(this.deliveries.size() - 1);
    }

    public boolean isAccepted() {
        return getLastDelivery().isAccepted();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskWindow that = (TaskWindow) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
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
