package pojoDatas;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserServicePojo {

    private String username;
    private String email;

    public UserServicePojo() {
    }

    public UserServicePojo(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "UserServicePojo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
