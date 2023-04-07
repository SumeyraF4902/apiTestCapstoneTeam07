package pojoDatas;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleServicePojo {

      /*  "resource": "book",
                "action": "deneme, write etc.",
                "app_id": 2*/


    private String resource;
    private String action;
    private Integer app_id;

    public RoleServicePojo() {
    }

    public RoleServicePojo(String resource, String action, Integer app_id) {
        this.resource = resource;
        this.action = action;
        this.app_id = app_id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    @Override
    public String toString() {
        return "RoleServicePojo{" +
                "resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", app_id=" + app_id +
                '}';
    }
}
