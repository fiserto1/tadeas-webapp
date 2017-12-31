package tadeas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDTO implements Serializable {
    private static final long serialVersionUID = 6086914795784430468L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDTO groupDTO = (GroupDTO) o;

        return id != null ? id.equals(groupDTO.id) : groupDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
