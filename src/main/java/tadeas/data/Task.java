package tadeas.data;

import tadeas.dto.TaskDTO;
import tadeas.dto.UserDTO;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private static final long serialVersionUID = 8220082182249564449L;

    private int id;
    private String name;
    private UserDTO issuer;
    private boolean active;
    private LocalDate issueDate;
    private String definition;

    public Task(TaskDTO taskDTO, UserDTO issuer) {
        this.id = taskDTO.getId();
        this.name = taskDTO.getName();
        this.issuer = issuer;
        this.active = taskDTO.isActive();
        this.issueDate = taskDTO.getIssueDate();
        this.definition = taskDTO.getDefinition();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getIssuer() {
        return issuer;
    }

    public void setIssuer(UserDTO issuer) {
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

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", issuer=" + issuer +
                ", active=" + active +
                ", issueDate=" + issueDate +
                ", definition='" + definition + '\'' +
                '}';
    }
}
