// JSON parser that inserts into SQL database
// Requires JSON.simple (json-simple-1.1.1.jar)
// Be sure to update database password in DriverManager

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class ExhibitionParser extends Parser {

    private static PreparedStatement exhibitionInsertStatement;

    static void parseExhibitionFile() {
        try {
            String exhibitionPrepStatement = "insert into exhibitions values (?, ?, ?, ?, ?, ?, ?)";
            exhibitionInsertStatement = con.prepareStatement(exhibitionPrepStatement);

            JSONParser parser = new JSONParser();

            File dir = new File("C:/json/exhibit");
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
                if (files != null) {
                    for (File file : files) {
                        Object exhibit = parser.parse(new FileReader(file));
                        JSONObject jsonObject = (JSONObject) exhibit;
                        parseExhibit(jsonObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseExhibit(JSONObject exhibit) throws SQLException {
        exhibition_id = getClean(exhibit, "exhibition_id");
        String exhibition_title = getClean(exhibit, "exhibition_title");
        String exhibition_description = "";
        String begin = getClean(exhibit, "begin");
        String end = getClean(exhibit, "end");
        String display_date = getClean(exhibit, "display_date");
        String department_id = generateDepartmentID((String) exhibit.get("exhibition_department"));
        JSONArray jsonObjects = (JSONArray) exhibit.get("objects");

        for (Object jsonObject : jsonObjects) {
            ObjectParser.parseObjectFile(String.valueOf(jsonObject));
        }

        exhibitionInsertStatement.setString(1, exhibition_id);
        exhibitionInsertStatement.setString(2, exhibition_title);
        exhibitionInsertStatement.setString(3, exhibition_description);
        exhibitionInsertStatement.setString(4, begin);
        exhibitionInsertStatement.setString(5, end);
        exhibitionInsertStatement.setString(6, display_date);
        exhibitionInsertStatement.setString(7, department_id);

        exhibitionInsertStatement.executeUpdate();
        numRows++;
        System.out.println("EXHIBITION inserted: " + exhibition_title);
    }
}