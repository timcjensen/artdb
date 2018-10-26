// JSON parser that inserts into SQL database
// Requires JSON.simple (json-simple-1.1.1.jar)
// Be sure to update database password in DriverManager

// TODO: 	Iterating over multiple JSON files - DONE
//			Support for multiple tables/different JSON files 
// 		 		JSON files: objects, exhibitions, departments
//				Tables:	
//			Cleaning/prepping data before inserting

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONtoMySQL {
	static String id;
	static String artist;
	static String country;
	static PreparedStatement prepStatement;
	static int numFiles = 0;

	public static void main(String[] args) throws Exception {
		System.out.println("Parsing JSON files, please wait...");
		long startTime = System.nanoTime();
		insertJSONtoDB();
		long endTime = System.nanoTime();
		long duration = ((endTime - startTime)/1000000);
		System.out.println("Parsing and insertion completed!");
		System.out.println("Parsed and inserted " + numFiles + " files in " + duration + " nanoseconds.");
	}

	public static void insertJSONtoDB() throws Exception {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/artdb", "root", "root");
			prepStatement = con.prepareStatement("insert into object values (?, ?, ?)");

			JSONParser parser = new JSONParser();

			File dir = new File("C:\\json");
			if (dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
				if (files != null) {
					for (File file : files) {
						numFiles++;
						Object obj = parser.parse(new FileReader(file));
						JSONObject jsonObject = (JSONObject) obj;
						jsonParser(jsonObject, prepStatement);
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
	public static void jsonParser(JSONObject jsonObject, PreparedStatement prepStatement) throws SQLException {
		id = (String) jsonObject.get("id");
		artist = cleanArtist((String) jsonObject.get("artist"));
		country = cleanCountry((String) jsonObject.get("country"));
		
		System.out.println("id length: " + id.length());
		System.out.println("Artist length: " + artist.length());
		System.out.println("Country length: " + country.length());

		prepStatement.setString(1, id);
		prepStatement.setString(2, artist);
		prepStatement.setString(3, country);

		prepStatement.executeUpdate();
	}
	
	
	// Methods that clean/prep data
	public static String cleanArtist(String artist) {
		if(artist.equals(null)||artist.equals("")) {
			artist = "Unknown artist";
		}
		return artist;
	}
	
	public static String cleanCountry(String country) {
		if(country.equals(null)||country.equals("")) {
			country = "Unknown artist";
		}
		return country;
	}
}
