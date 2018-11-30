package db;

import javax.persistence.Id;

import org.hibernate.search.annotations.Field;

public class Department {
    @Id public int Department_id;
    @Field public String Department_name;

    public Department(int department_id, String department_name) {
        Department_id = department_id;
        Department_name = department_name;
    }

    public Department() {
    }

    public int getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(int department_id) {
        Department_id = department_id;
    }

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }
}
