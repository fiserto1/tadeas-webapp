package tadeas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryWindowDTO implements Serializable {
    private static final long serialVersionUID = -8393921304784590261L;

    private int id;
    private TaskDTO task;
    private int issuer;
    private boolean active;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate issueDate;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate deadlineDate;
    private String definition;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryWindowDTO that = (DeliveryWindowDTO) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "DeliveryWindowDTO{" +
                "id=" + id +
                ", task=" + task +
                ", issuer=" + issuer +
                ", active=" + active +
                ", issueDate=" + issueDate +
                ", startDate=" + startDate +
                ", deadlineDate=" + deadlineDate +
                ", definition='" + definition + '\'' +
                '}';
    }
}
