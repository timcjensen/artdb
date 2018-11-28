package db;

public class Culture_info {
    public int Culture_id;
    public String Continent;
    public String Country;

    public Culture_info(int culture_id, String continent, String country) {
        Culture_id = culture_id;
        Continent = continent;
        Country = country;
    }

    public Culture_info() {
    }

    public int getCulture_id() {
        return Culture_id;
    }

    public void setCulture_id(int culture_id) {
        Culture_id = culture_id;
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
