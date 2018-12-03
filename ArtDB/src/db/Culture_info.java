package db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Culture_info {
    @Id private int culture_id;
    @Field private String Continent;
    @Field private String Country;

    public Culture_info(int id, String continent, String country) {
    	super();
        culture_id = id;
        Continent = continent;
        Country = country;
    }

    public Culture_info() {
    	super();
    }

    public int getCulture_id() {
        return culture_id;
    }

    public void setCulture_id(int id) {
        culture_id = id;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
