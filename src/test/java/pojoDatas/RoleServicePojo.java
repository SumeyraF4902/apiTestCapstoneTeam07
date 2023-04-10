package pojoDatas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleServicePojo {
    /*[
    {
        "id": 17,
        "name": "A3M_ADMIN",
        "app_id": 2
    },
    {
        "id": 23,
        "name": "Accountant",
        "app_id": 2
    },
    {
        "id": 4,
        "name": "APP_DOMAIN_MANAGER",
        "app_id": 2
    },
    {
        "id": 30,
        "name": "Customer",
        "app_id": 2
    },
    {
        "id": 6,
        "name": "Guest",
        "app_id": 2
    },
    {
        "id": 26,
        "name": "Logistics Manager",
        "app_id": 2
    },
    {
        "id": 27,
        "name": "Logistics Personnel",
        "app_id": 2
    },
    {
        "id": 21,
        "name": "Purchase Manager",
        "app_id": 2
    },
    {
        "id": 22,
        "name": "Purchase Personnel",
        "app_id": 2
    },
    {
        "id": 29,
        "name": "Quality Controller",
        "app_id": 2
    },
    {
        "id": 28,
        "name": "Quality Manager",
        "app_id": 2
    },
    {
        "id": 19,
        "name": "Sales Manager",
        "app_id": 2
    },
    {
        "id": 20,
        "name": "Sales Personnel",
        "app_id": 2
    },
    {
        "id": 18,
        "name": "Store Manager",
        "app_id": 2
    },
    {
        "id": 24,
        "name": "Warehouse Manager",
        "app_id": 2
    },
    {
        "id": 25,
        "name": "Warehouse Personnel",
        "app_id": 2
    },
    {
        "id": 5,
        "name": "Business Owner",
        "app_id": 2
    }
]*/

    private Integer id;
    private String name;
    private Integer app_id;

    public RoleServicePojo() {
    }

    public RoleServicePojo(Integer id, String name, Integer app_id) {
        this.id = id;
        this.name = name;
        this.app_id = app_id;
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

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    @Override
    public String toString() {
        return "RoleServicePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", app_id=" + app_id +
                '}';

    }
}