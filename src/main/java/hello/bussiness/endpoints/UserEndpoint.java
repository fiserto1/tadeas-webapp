package hello.bussiness.endpoints;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEndpoint {


        private Integer id;
        private String userName;
        private String firstName;
        private String lasName;
        private String email;
        private String role;
        private List<GroupEndpoint> groups;
        protected String sessionKey;

        public UserEndpoint() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLasName() {
            return lasName;
        }

        public void setLasName(String lasName) {
            this.lasName = lasName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public List<GroupEndpoint> getGroups() {
            return groups;
        }

        public void setGroups(List<GroupEndpoint> groups) {
            this.groups = groups;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

    @Override
    public String toString() {
        return "LoginEndpoint{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lasName='" + lasName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", groups=" + groups +
                ", sessionKey='" + sessionKey + '\'' +
                '}';
    }
}
