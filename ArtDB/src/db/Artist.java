package db;

public class Artist {
	private int artist_id;
	private String Artist_name;
	private String Life_date;
	private String Nationality;
	
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
