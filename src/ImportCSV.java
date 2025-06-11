import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImportCSV {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Charles\\IdeaProjects\\personal playlist\\songs_playlist.csv"; // adjust if needed
        String dbUrl = "jdbc:sqlite:C:/Users/Charles/IdeaProjects/personal playlist/music.db";

        String insertSQL = "INSERT INTO songs (artist, title, mood, link) VALUES (?, ?, ?, ?)";

        try (
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)
        ) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip empty lines
                String[] data = line.split(",", -1); // handle empty values

                if (data.length < 4) continue;

                pstmt.setString(1, data[0].trim()); // artist
                pstmt.setString(2, data[1].trim()); // title
                pstmt.setString(3, data[2].trim()); // mood
                pstmt.setString(4, data[3].trim()); // link
                pstmt.addBatch();

                count++;
            }

            pstmt.executeBatch();
            System.out.println("✅ Successfully imported " + count + " songs!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

