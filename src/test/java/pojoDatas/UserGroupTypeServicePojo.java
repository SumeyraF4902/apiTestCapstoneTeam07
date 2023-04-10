package pojoDatas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"id"})
public class UserGroupTypeServicePojo {

        @JsonIgnore
        private Integer id;
        private String name;
        private String description;

    public UserGroupTypeServicePojo() {
    }

    public UserGroupTypeServicePojo(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UserGroupTypeServicePojo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "UserGroupTypeService{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

