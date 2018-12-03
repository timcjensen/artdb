package db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Department {
    @Id private int department_id;
    @Field private String Department_name;

    public Department(int id, String department_name) {
    	super();
        department_id = id;
        Department_name = department_name;
    }

    public Department() {
    	super();
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int id) {
        department_id = id;
    }

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }
}
