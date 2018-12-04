package db;

import javax.persistence.Id;
import org.apache.commons.text.StringEscapeUtils;
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
		String image = "http://api.artsmia.org/images/" + object_id + "/small.jpg";
		StringBuilder result = new StringBuilder();
		result.append("<td>");
		result.append("<form action=\"DetailedObject\" method=\"post\">\r\n" + 
				"  <button type=\"submit\" name=\"object_id\" value=\"" + object_id + "\" class=\"btn-link\">More info</button>\r\n" + 
				"</form>\r\n");
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Title));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Description));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Artist_name));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Country));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Department_name));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Exhibition_title));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Room_name));
		result.append("</td>");
		result.append("<td>");
		result.append(StringEscapeUtils.escapeHtml4(Dimensions));
		result.append("</td>");
		result.append("<td>");
		result.append("<a href=\"" + image + "\" target=\"_blank\">");
		result.append("<img src=\"" + image + "\" height=100 width=100></img>");
		result.append("</a>");
		result.append("</td>");

		return result.toString();
	}
	
	public String fullInfo() {
		String image = "http://api.artsmia.org/images/" + object_id + "/small.jpg";
		StringBuilder result = new StringBuilder();
		result.append("<h1>" + Title + "</hr><br>");
		result.append("<h2> By " + Artist_name + "</hr><br>");
		result.append("<h3>Description: " + Description + "</hr><br>");
		result.append("<h3>Signature: " + Signature + "</hr><br>");
		result.append("<h3>Dated: " + Dated + "</hr><br>");
		result.append("<h3>Signature: " + Signature + "</hr><br>");
		result.append("<h3>Markings: " + Markings + "</hr><br>");
		result.append("<h3>Style: " + Style + "</hr><br>");
		result.append("<h3>Credit line: " + Credit_line + "</hr><br>");
		result.append("<h3>Accession number: " + Accession_number + "</hr><br>");
		result.append("<br>");
		result.append("<h3>Artist life: " + Life_date + "</hr><br>");
		result.append("<h3>Artist nationality: " + Nationality + "</hr><br>");
		result.append("<br>");
		result.append("<h3>Continent: " + Continent + "</hr><br>");
		result.append("<h3>Country: " + Country + "</hr><br>");
		result.append("<h3>Department: " + Department_name + "</hr><br>");
		result.append("<h3>Exhibition: " + Exhibition_title + "</hr><br>");
		result.append("<h3>Exhibition display date: " + Display_date + "</hr><br>");
		result.append("<h3>Room: " + Room_name + "</hr><br>");
		result.append("<h3>Dimensions: " + Dimensions + "</hr><br>");
		result.append("<img src=\"" + image + "\" height=100 width=100></img>");

		return result.toString();
	}
	
	public boolean selection() {
		return true;
	}
}
