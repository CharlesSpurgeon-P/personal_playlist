package com.charles.playlist;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

public class SearchEngine {
    public static void openLinks(List<Song> songs) {
        for (Song song : songs) {
            try {
                Desktop.getDesktop().browse(new URI(song.link));
                System.out.println("🔗 Opening: " + song.link);
            } catch (Exception e) {
                System.out.println("❌ Failed to open: " + song.link);
            }
        }
    }
}
