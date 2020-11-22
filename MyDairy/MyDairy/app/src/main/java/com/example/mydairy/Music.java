package com.example.mydairy;

public class Music {

    private String musicTitle;
    private String musicUri;

    public Music(){}

    public Music(String musicTitle, String musicUri){
        this.musicTitle = musicTitle;
        this.musicUri = musicUri;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public String getMusicUri() {
        return musicUri;
    }

    public void setMusicName(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public void setMusicUri(String musicUri) {
        this.musicUri = musicUri;
    }
}
