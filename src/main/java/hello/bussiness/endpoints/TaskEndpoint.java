package hello.bussiness.endpoints;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskEndpoint {
    private int id;
    private String name;
    private int issuer;
    private boolean active;
    private Date issueDate;
    private String definition;

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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "TaskEndpoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", issuer=" + issuer +
                ", active=" + active +
                ", issueDate=" + issueDate +
                ", definition='" + definition + '\'' +
                '}';
    }
}
