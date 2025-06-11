package com.charles.playlist;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                \n🎵 Playlist Engine:
                1. Add Song
                2. Delete Song by Title
                3. Search by Mood
                4. Search by Title/Artist
                5. Exit
                """);
            System.out.print("Choose option: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Artist: "); String a = sc.nextLine();
                    System.out.print("Title: "); String t = sc.nextLine();
                    System.out.print("Mood: "); String m = sc.nextLine();
                    System.out.print("Link: "); String l = sc.nextLine();
                    DBHelper.addSong(a, t, m, l);
                }
                case 2 -> {
                    System.out.print("Title to delete: ");
                    DBHelper.deleteSong(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Mood: ");
                    List<Song> results = DBHelper.searchByMood(sc.nextLine());
                    results.forEach(System.out::println);
                    SearchEngine.openLinks(results);
                }
                case 4 -> {
                    System.out.print("Search: ");
                    List<Song> results = DBHelper.searchByTitleOrArtist(sc.nextLine());
                    results.forEach(System.out::println);
                    SearchEngine.openLinks(results);
                }
                case 5 -> System.exit(0);
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}

