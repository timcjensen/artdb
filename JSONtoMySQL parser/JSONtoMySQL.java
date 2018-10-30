// JSON parser that inserts into SQL database
// Requires JSON.simple (json-simple-1.1.1.jar)
// Be sure to update database password in DriverManager

// TODO: 	Iterating over multiple JSON files - DONE
//			Support for multiple tables/different JSON files 
// 		 		JSON files: objects, exhibitions, departments
//			Cleaning/prepping data before inserting

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONtoMySQL {

	// object columns
	static String object_id; // 1
	static String title; // 2
	static String description; // 3
	static String signature; // 4
	static String dated; // 5
	static String markings; // 6
	static String style; // 7
	static String classification; // 8
	static String approval; // 9
	static String credit_line; // 10
	static String accession_number; // 11
	static String artist_id; // 12
	static String artist_name; // 13
	static String culture_id; // 14
	static String room_id; // 15
	static String department_id; // 16
	static String exhibition_id; // 17
	static String spec_id; // 18

	// artist columns
	static String life_date;
	static String nationality;
	static String portfolio_id;
	
	static String[][] departments = {{"13", "Japanese and Korean Art"},
			{"14", "Contemporary Art"},
			{"10", "Minnesota Artists Exhibition Program"},
			{"7", "Photography and New Media"},
			{"6", "Paintings"},
			{"5", "Textiles"},
			{"4", "Decorative Arts, Textiles and Sculpture"},
			{"1", "Chinese, South and Southeast Asian Art"},
			{"2", "Prints and Drawings"},
			{"8", "Art of Africa and the Americas"}};
		
	private static String connectionURL = "jdbc:mysql://localhost:3306/artdb";
	private static String connectionUser = "root";
	private static String connectionPassword = "root";

	static String objectPrepStatement = "insert into object values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	static String artistPrepStatement = "insert into artist values (?, ?, ?, ?, ?)";

	static PreparedStatement objectInsertStatement;
	static PreparedStatement artistInsertStatement;

	static int numFiles = 0;

	static Connection con = null;

	public static void main(String[] args) throws Exception {
		System.out.println("Parsing JSON files, please wait...");

		long startTime = System.nanoTime();
		insertJSONtoDB();
		long endTime = System.nanoTime();

		long duration = ((endTime - startTime) / 1000000);

		System.out.println("Parsing and insertion completed!");
		System.out.println("Parsed and inserted " + numFiles + " files in " + duration + " nanoseconds.");
	}

	public static void insertJSONtoDB() throws Exception {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(connectionURL, connectionUser, connectionPassword);
			objectInsertStatement = con.prepareStatement(objectPrepStatement);
			artistInsertStatement = con.prepareStatement(artistPrepStatement);

			JSONParser parser = new JSONParser();

			File dir = new File("C:\\json");
			if (dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
				if (files != null) {
					for (File file : files) {
						numFiles++;
						Object obj = parser.parse(new FileReader(file));
						JSONObject jsonObject = (JSONObject) obj;
						parseObject(jsonObject, objectInsertStatement);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	// Parses and executes prepared statement
	public static void parseObject(JSONObject jsonObject, PreparedStatement prepStatement) throws SQLException {
		object_id = (String) jsonObject.get("id");
		object_id = object_id.substring(31);

		title = (String) jsonObject.get("title");
		title = truncateLongString(title);

		description = (String) jsonObject.get("description");
		description = truncateLongString(description);

		signature = (String) jsonObject.get("signed");
		dated = (String) jsonObject.get("dated");
		markings = (String) jsonObject.get("markings");
		markings = truncateLongString(markings);

		style = (String) jsonObject.get("style");
		classification = (String) jsonObject.get("classification");
		approval = jsonObject.get("curator_approved") + "";
		credit_line = (String) jsonObject.get("creditline");
		accession_number = "0"; // (String) jsonObject.get("accession_number");
		artist_name = (String) jsonObject.get("artist");
		artist_name = cleanUnknown(artist_name);
		artist_name = truncateLongString(artist_name);

		life_date = cleanUnknown((String) jsonObject.get("life_date"));
		nationality = cleanUnknown((String) jsonObject.get("nationality"));
		portfolio_id = "0";

		artist_id = generateArtistID(artist_name, jsonObject);
		culture_id = generateCultureID((String) jsonObject.get("culture"));
		room_id = generateRoomID((String) jsonObject.get("room"));
		department_id = generateDepartmentID((String) jsonObject.get("department"));
		exhibition_id = "0"; // TODO: figure out what to do here
		spec_id = generateSpecID(jsonObject.get("image_height") + "", jsonObject.get("image_width") + "");

		prepStatement.setString(1, object_id);
		prepStatement.setString(2, title);
		prepStatement.setString(3, description);
		prepStatement.setString(4, signature);
		prepStatement.setString(5, dated);
		prepStatement.setString(6, markings);
		prepStatement.setString(7, style);
		prepStatement.setString(8, classification);
		prepStatement.setString(9, approval);
		prepStatement.setString(10, credit_line);
		prepStatement.setString(11, accession_number);
		prepStatement.setString(12, artist_id);
		prepStatement.setString(13, artist_name);
		prepStatement.setString(14, culture_id);
		prepStatement.setString(15, room_id);
		prepStatement.setString(16, department_id);
		prepStatement.setString(17, exhibition_id);
		prepStatement.setString(18, spec_id);

		prepStatement.executeUpdate();
		System.out.println("Succesfully inserted: " + title);
	}

	public static String truncateLongString(String input) {
		if (input.length() > 200) {
			return input.substring(0, 197) + "...";
		} else
			return input;
	}

	// Returns artist id from artist name
	// If no generated id, make new one
	public static String generateArtistID(String artistName, JSONObject jsonObject) throws SQLException {
		PreparedStatement artistCount = con.prepareStatement("SELECT count(1) from artdb.artist WHERE artist_name = \""
				+ artistName + "\" AND life_date = \"" + life_date + "\" AND nationality = \"" + nationality + "\"");
		PreparedStatement artistInfo = con.prepareStatement("SELECT artist_id from artdb.artist WHERE artist_name = \""
				+ artistName + "\" AND life_date = \"" + life_date + "\" AND nationality = \"" + nationality + "\"");
		ResultSet count = artistCount.executeQuery();

		count.next();
		if (count.getString(1).equals("0")) { // 0 = artist does not exist in table
			Random rnd = new Random();
			String artist_id = rnd.nextInt(999999) + "";
			artistInsertStatement.setString(1, artist_id);
			artistInsertStatement.setString(2, artist_name);
			artistInsertStatement.setString(3, life_date);
			artistInsertStatement.setString(4, nationality);
			artistInsertStatement.setString(5, portfolio_id);

			// System.out.println("Arist id CREATED: " + artist_id);

			artistInsertStatement.executeUpdate();
			return artist_id;
		}

		else if (count.getString(1).equals("1")) { // 1 = artist exists in table
			ResultSet artist = artistInfo.executeQuery();
			artist.next();

			// System.out.println("Arist id: " + artist.getString(1));

			return artist.getString(1);
		}

		else
			return "error";
	}

	public static String generateCultureID(String cultureName) {
		return "0";
	}

	public static String generateRoomID(String roomName) {
		return "0";
	}

	// Returns department id from department name
	// Because limited num of departments, use table lookup
	public static String generateDepartmentID(String department) {
		for(int i = 0; i < departments.length ; i++) {
			if(departments[i][1].equals(department)) {
				return departments[i][0];
			}
		}
		return "99";
	}

	public static String generateSpecID(String image_height, String image_width) {
		return "0";
	}

	// Methods that clean/prep data
	public static String cleanUnknown(String input) {
		if (input == null || input.equals("")) {
			input = "Unknown";
		}
		return input;
	}
}
