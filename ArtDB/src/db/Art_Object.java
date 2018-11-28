package db;

public class Art_Object {
	private int object_id;
	private String Title;
	private String Description;
	private String Signature;
	private String Dated;
	private String Markings;
	private String Style;
	private String Classification;
	private String Approval;
	private String Credit_line;
	private String Accession_number;
	private String Artist_id;
	private String Culture_id;
	private String Room_id;
	private String Exhibition_id;
	private String Department_id;
	private String Spec_id;

	public Art_Object() {
		super();
	}

	public Art_Object(int iD, String title, String description, String signature, String dated, String markings,
			String style, String classification, String approval, String credit_line, String accession_number,
			String artist_id, String culture_id, String room_id, String exhibition_id, String department_id,
			String spec_id) {
		super();
		object_id = iD;
		Title = title;
		Description = description;
		Signature = signature;
		Dated = dated;
		Markings = markings;
		Style = style;
		Classification = classification;
		Approval = approval;
		Credit_line = credit_line;
		Accession_number = accession_number;
		Artist_id = artist_id;
		Culture_id = culture_id;
		Room_id = room_id;
		Exhibition_id = exhibition_id;
		Department_id = department_id;
		Spec_id = spec_id;
	}

	public int getObject_id() {
		return object_id;
	}

	public void setObject_id(int iD) {
		object_id = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getDated() {
		return Dated;
	}

	public void setDated(String dated) {
		Dated = dated;
	}

	public String getMarkings() {
		return Markings;
	}

	public void setMarkings(String markings) {
		Markings = markings;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

	public String getClassification() {
		return Classification;
	}

	public void setClassification(String classification) {
		Classification = classification;
	}

	public String getApproval() {
		return Approval;
	}

	public void setApproval(String approval) {
		Approval = approval;
	}

	public String getCredit_line() {
		return Credit_line;
	}

	public void setCredit_line(String credit_line) {
		Credit_line = credit_line;
	}

	public String getAccession_number() {
		return Accession_number;
	}

	public void setAccession_number(String accession_number) {
		Accession_number = accession_number;
	}

	public String getArtist_id() {
		return Artist_id;
	}

	public void setArtist_id(String artist_id) {
		Artist_id = artist_id;
	}

	public String getCulture_id() {
		return Culture_id;
	}

	public void setCulture_id(String culture_id) {
		Culture_id = culture_id;
	}

	public String getRoom_id() {
		return Room_id;
	}

	public void setRoom_id(String room_id) {
		Room_id = room_id;
	}

	public String getExhibition_id() {
		return Exhibition_id;
	}

	public void setExhibition_id(String exhibition_id) {
		Exhibition_id = exhibition_id;
	}

	public String getDepartment_id() {
		return Department_id;
	}

	public void setDepartment_id(String department_id) {
		Department_id = department_id;
	}

	public String getSpec_id() {
		return Spec_id;
	}

	public void setSpec_id(String spec_id) {
		Spec_id = spec_id;
	}
}
