package com.charles.playlist;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String URL = "jdbc:sqlite:C:/Users/Charles/IdeaProjects/personal playlist/music.db";


    public static void addSong(String artist, String title, String mood, String link) {
        String sql = "INSERT INTO songs (Artist, Title, Mood, Link) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, artist);
            pstmt.setString(2, title);
            pstmt.setString(3, mood);
            pstmt.setString(4, link);
            pstmt.executeUpdate();
            System.out.println("🎵 Song added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSong(String title) {
        String sql = "DELETE FROM songs WHERE Title = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            int affected = pstmt.executeUpdate();
            System.out.println(affected > 0 ? "🗑️ Deleted!" : "❌ Not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Song> searchByMood(String mood) {
        String sql = "SELECT * FROM songs WHERE Mood = ? ORDER BY RANDOM() LIMIT 3";
        List<Song> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mood);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new Song(
                        rs.getInt("id"),
                        rs.getString("Artist"),
                        rs.getString("Title"),
                        rs.getString("Mood"),
                        rs.getString("Link")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Song> searchByTitleOrArtist(String input) {
        String sql = "SELECT * FROM songs WHERE Title LIKE ? OR Artist LIKE ?";
        List<Song> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + input + "%");
            pstmt.setString(2, "%" + input + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new Song(
                        rs.getInt("id"),
                        rs.getString("Artist"),
                        rs.getString("Title"),
                        rs.getString("Mood"),
                        rs.getString("Link")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

