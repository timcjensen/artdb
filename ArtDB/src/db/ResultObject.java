package db;

import javax.persistence.Id;

import org.hibernate.search.annotations.Field;

public class ResultObject {
	@Id private int object_id;
	@Field private String Title;
	@Field private String Description;
	@Field private String Signature;
	@Field private String Dated;
	@Field private String Markings;
	@Field private String Style;
	@Field private String Classification;
	@Field private String Approval;
	@Field private String Credit_line;
	@Field private String Accession_number;
	
	@Field private String Artist_name;
	@Field private String Life_date;
	@Field private String Nationality;
	
	@Field public String Continent;
	@Field public String Country;

	@Field public String Department_name;

	@Field private String Exhibition_title;
	@Field private String Exhibition_description;
	@Field private String Begin;
	@Field private String End;
	@Field private String Display_date;

	@Field private String Room_name;
    
	@Field private String Dimensions;

	public ResultObject(int object_id) {
		super();
		this.object_id = object_id;
		
		Art_Object art = HibernateGetter.getArt_Object(object_id);
		int artist_id = Integer.parseInt(art.getArtist_id());
		int culture_id = Integer.parseInt(art.getCulture_id());
		int room_id = Integer.parseInt(art.getRoom_id());
		int exhibition_id = Integer.parseInt(art.getExhibition_id());
		int department_id = Integer.parseInt(art.getDepartment_id());
		int spec_id = Integer.parseInt(art.getSpec_id());
		
		Artist artist = HibernateGetter.getArtist(artist_id);
		Culture_info culture = HibernateGetter.getCulture_info(culture_id);
		Room room = HibernateGetter.getRoom(room_id);
		Exhibitions exhibition = HibernateGetter.getExhibition(exhibition_id);
		Department department = HibernateGetter.getDepartment(department_id);
		Specs spec = HibernateGetter.getSpec(spec_id);
		
		Title = art.getTitle();
		Description = art.getDescription();
		Signature = art.getSignature();
		Dated = art.getDated();
		Markings = art.getMarkings();
		Style = art.getStyle();
		Classification = art.getClassification();
		Approval = art.getApproval();
		Credit_line = art.getCredit_line();
		Accession_number = art.getAccession_number();
		
		Artist_name = artist.getArtist_name();
		Life_date = artist.getLife_date();
		Nationality = artist.getNationality();
		
		Continent = culture.getContinent();
		Country = culture.getCountry();
		
		Department_name = department.getDepartment_name();
		
		Exhibition_title = exhibition.getExhibition_title();
		Exhibition_description = exhibition.getExhibition_description();
		Begin = exhibition.getBegin();
		End = exhibition.getEnd();
		Display_date = exhibition.getDisplay_date();
		
		Room_name = room.getRoom_name();
		
		Dimensions = spec.getDimensions();
	}

	public int getObject_id() {
		return object_id;
	}

	public String getTitle() {
		return Title;
	}

	public String getDescription() {
		return Description;
	}

	public String getSignature() {
		return Signature;
	}

	public String getDated() {
		return Dated;
	}

	public String getMarkings() {
		return Markings;
	}

	public String getStyle() {
		return Style;
	}

	public String getClassification() {
		return Classification;
	}

	public String getApproval() {
		return Approval;
	}

	public String getCredit_line() {
		return Credit_line;
	}

	public String getAccession_number() {
		return Accession_number;
	}

	public String getArtist_name() {
		return Artist_name;
	}

	public String getLife_date() {
		return Life_date;
	}

	public String getNationality() {
		return Nationality;
	}

	public String getContinent() {
		return Continent;
	}

	public String getCountry() {
		return Country;
	}

	public String getDepartment_name() {
		return Department_name;
	}

	public String getExhibition_title() {
		return Exhibition_title;
	}

	public String getExhibition_description() {
		return Exhibition_description;
	}

	public String getBegin() {
		return Begin;
	}

	public String getEnd() {
		return End;
	}

	public String getDisplay_date() {
		return Display_date;
	}

	public String getRoom_name() {
		return Room_name;
	}

	public String getDimensions() {
		return Dimensions;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(Title + ", ");
		result.append(Description + ", ");
		result.append(Artist_name + ", ");
		result.append(Department_name + ", ");
		result.append(Exhibition_title + ", ");
		
		return result.toString();
	}
	
	public boolean selection() {
		return true;
	}
}
