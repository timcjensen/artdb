package db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Artist {
	@Id private int artist_id;
	@Field private String Artist_name;
	@Field private String Life_date;
	@Field private String Nationality;
	
	public Artist() {
		super();
	}

	public Artist(int id, String artist_name, String life_date, String nationality) {
		super();
		artist_id = id;
		Artist_name = artist_name;
		Life_date = life_date;
		Nationality = nationality;
	}

	public int getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(int iD) {
		artist_id = iD;
	}

	public String getArtist_name() {
		return Artist_name;
	}

	public void setArtist_name(String artist_name) {
		Artist_name = artist_name;
	}

	public String getLife_date() {
		return Life_date;
	}

	public void setLife_date(String life_date) {
		Life_date = life_date;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		this.Nationality = nationality;
	}
}
