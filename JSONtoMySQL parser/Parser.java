import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Parser {
    static Connection con = null;

    static int numRows = 0;
    static String exhibition_id = "0";

    private static String[][] departments = {{"13", "Japanese and Korean Art"}, {"14", "Contemporary Art"},
            {"10", "Minnesota Artists Exhibition Program"}, {"7", "Photography and New Media"},
            {"6", "Paintings"}, {"5", "Textiles"}, {"4", "Decorative Arts, Textiles and Sculpture"},
            {"1", "Chinese, South and Southeast Asian Art"}, {"2", "Prints and Drawings"},
            {"8", "Art of Africa and the Americas"}};

    public static void main(String[] args) {
        createConnection();
        System.out.println("Parsing JSON files, please wait...");

        long startTime = System.nanoTime();
        ExhibitionParser.parseExhibitionFile();

        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 1000000);

        System.out.println("Parsing and insertion completed!");
        System.out.println("Parsed and inserted " + numRows + " rows in " + duration + " nanoseconds.");
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL = "jdbc:mysql://localhost:3306/artdb";
            String connectionPassword = "root";
            String connectionUser = "root";
            con = DriverManager.getConnection(connectionURL, connectionUser, connectionPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getClean(JSONObject jsonObject, String input) {
        String output = String.valueOf(jsonObject.get(input));
        output = cleanUnknown(output);
        output = truncateLongString(output);

        return output;
    }

    private static String cleanUnknown(String input) {
        if (input == null || input.equals("") || input.equals("null")) {
            input = "Unknown";
        }
        return input;
    }

    private static String truncateLongString(String input) {
        if (input.length() > 200) {
            return input.substring(0, 197) + "...";
        } else
            return input;
    }

    static String generateDepartmentID(String department) {
        for (String[] department1 : departments) {
            if (department1[1].equals(department)) {
                return department1[0];
            }
        }
        return "99";
    }
}
