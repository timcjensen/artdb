// JSON parser that inserts into SQL database
// Requires JSON.simple (json-simple-1.1.1.jar)
// Be sure to update database password in DriverManager

// TODO: 	Iterating over multiple JSON files - DONE
//			Support for multiple tables/different JSON files 
// 		 		JSON files: objects, exhibitions, departments
//			Cleaning/prepping data before inserting

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Random;

public class ObjectParser extends Parser {
    // artist columns
    private static String life_date;
    private static String nationality;
    private static String portfolio_id;

    static void parseObjectFile(String object_id) {
        try {
            String objectPrepStatement = "insert ignore into object values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement objectInsertStatement = con.prepareStatement(objectPrepStatement);

            JSONParser parser = new JSONParser();

            String filepath = "C:/json/object/";
            File file = new File(filepath + object_id + ".json");
            if (file.exists()) {
                Object obj = parser.parse(new FileReader(file));
                JSONObject jsonObject = (JSONObject) obj;
                parseObject(jsonObject, objectInsertStatement);
            } else
                System.out.println("Object_id " + object_id + " not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Parses and executes prepared statement
    private static void parseObject(JSONObject jsonObject, PreparedStatement prepStatement) throws SQLException {
        String object_id = ((String) jsonObject.get("id")).substring(31);

        String title = getClean(jsonObject, "title");
        String description = getClean(jsonObject, "description");
        String signature = getClean(jsonObject, "signed");

        String dated = getClean(jsonObject, "dated");
        String markings = getClean(jsonObject, "markings");

        String style = getClean(jsonObject, "style");
        String classification = getClean(jsonObject, "classification");
        String approval = getClean(jsonObject, "curator_approved");
        String credit_line = getClean(jsonObject, "creditline");
        String accession_number = "0"; // (String) jsonObject.get("accession_number");

        String artist_name = getClean(jsonObject, "artist");
        life_date = getClean(jsonObject, "life_date");
        nationality = getClean(jsonObject, "nationality");
        portfolio_id = "0";

        String artist_id = generateArtistID(artist_name);

        String country = getClean(jsonObject, "country");
        String continent = getClean(jsonObject, "continent");
        String culture_id = generateCultureID(country, continent);
        String room_id = generateRoomID((String) jsonObject.get("room"));
        String department_id = generateDepartmentID((String) jsonObject.get("department"));
        String spec_id = generateSpecID(jsonObject.get("image_height") + "", jsonObject.get("image_width") + "");

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
        numRows++;
        System.out.println("OBJECT inserted: " + title);
    }

    // Returns artist id from artist name
    // If no generated id, make new one
    private static String generateArtistID(String artistName) throws SQLException {
        PreparedStatement artistInsertStatement = con.prepareStatement("insert into artist values (?, ?, ?, ?, ?)");
        PreparedStatement artistInfo = con.prepareStatement("SELECT artist_id from artdb.artist WHERE artist_name = ? AND life_date = ? AND nationality = ?");
        artistInfo.setString(1, artistName);
        artistInfo.setString(2, life_date);
        artistInfo.setString(3, nationality);

        ResultSet info = artistInfo.executeQuery();

        if (!info.next()) {
            Random rnd = new Random();
            String artist_id = rnd.nextInt(999999) + "";
            artistInsertStatement.setString(1, artist_id);
            artistInsertStatement.setString(2, artistName);
            artistInsertStatement.setString(3, life_date);
            artistInsertStatement.setString(4, nationality);
            artistInsertStatement.setString(5, portfolio_id);

            artistInsertStatement.executeUpdate();
            System.out.println("ARTIST inserted: " + artistName);
            numRows++;
            return artist_id;
        } else {
            return info.getString(1);
        }
    }

    private static String generateCultureID(String country, String continent) throws SQLException {
        PreparedStatement cultureInfo = con.prepareStatement("SELECT culture_id from artdb.culture_info WHERE country = ? AND continent = ?");
        PreparedStatement cultureInsert = con.prepareStatement("insert into culture_info values (?, ?, ?)");
        cultureInfo.setString(1, country);
        cultureInfo.setString(2, continent);

        ResultSet info = cultureInfo.executeQuery();

        if (!info.next()) {
            Random rnd = new Random();
            String culture_id = rnd.nextInt(999999) + "";
            cultureInsert.setString(1, culture_id);
            cultureInsert.setString(2, continent);
            cultureInsert.setString(3, country);

            cultureInsert.executeUpdate();
            System.out.println("CULTURE inserted: " + continent + ", " + country);
            numRows++;
            return culture_id;
        } else {
            return info.getString(1);
        }
    }

    private static String generateRoomID(String roomName) throws SQLException {
        PreparedStatement roomInfo = con.prepareStatement("SELECT room_id from artdb.room WHERE room_name = ?");
        PreparedStatement roomInsert = con.prepareStatement("insert into room values (?, ?)");
        roomInfo.setString(1, roomName);

        ResultSet info = roomInfo.executeQuery();

        if (!info.next()) {
            Random rnd = new Random();
            String room_id = rnd.nextInt(999999) + "";
            roomInsert.setString(1, room_id);
            roomInsert.setString(2, roomName);

            roomInsert.executeUpdate();
            System.out.println("ROOM inserted: " + roomName);
            numRows++;
            return room_id;
        } else {
            return info.getString(1);
        }
    }

    private static String generateSpecID(String image_height, String image_width) throws SQLException {
        PreparedStatement specInfo = con.prepareStatement("SELECT spec_id from artdb.specs WHERE dimensions = ?");
        PreparedStatement specInsert = con.prepareStatement("insert into specs values (?, ?)");
        String dimensions = image_height + "x" + image_width;
        specInfo.setString(1, dimensions);

        ResultSet info = specInfo.executeQuery();

        if (!info.next()) {
            Random rnd = new Random();
            String spec_id = rnd.nextInt(999999) + "";
            specInsert.setString(1, spec_id);
            specInsert.setString(2, dimensions);

            specInsert.executeUpdate();
            System.out.println("SPECS inserted: " + dimensions);
            numRows++;
            return spec_id;
        } else {
            return info.getString(1);
        }
    }
}