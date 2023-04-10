package pojoDatas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationServicePojo {



 /*
 {
  "id": 1,
  "name": "TEAM00767",
  "founder_id": 2,
  "short_name": "team",
  "address": "Ronald Avenue McMillan Drive No. 5, Tysons, Virginia",
  "phone": "15555555555",
  "email": "contactacme.com",
  "website": "www.acme.com",
  "fax": "1555555555",
  "status_id": 1
   "created_at": "2023-04-06T21:24:42.939062Z",
   "updated_at": "2023-04-06T21:24:42.939064Z"


 }
  */

   private Integer id;
   private String name;
   private Integer founder_id;
   private String short_name;
   private String address;
   private String phone;
   private String email;
   private String website;
   private String fax;
   private Integer status_id;
   private String created_at;
   private String updated_at;


    public OrganizationServicePojo() {
    }

    public OrganizationServicePojo(Integer id, String name, Integer founder_id, String short_name, String address, String phone, String email, String website, String fax, Integer status_id, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.founder_id = founder_id;
        this.short_name = short_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.fax = fax;
        this.status_id = status_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public Integer getFounder_id() {
        return founder_id;
    }

    public void setFounder_id(Integer founder_id) {
        this.founder_id = founder_id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", founder_id=" + founder_id +
                ", short_name='" + short_name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", fax='" + fax + '\'' +
                ", status_id=" + status_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }



}
