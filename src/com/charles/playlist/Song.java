package com.charles.playlist;

public class Song {
    public int id;
    public String artist, title, mood, link;

    public Song(int id, String artist, String title, String mood, String link) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.mood = mood;
        this.link = link;
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + mood + ") → " + link;
    }
}
