package com.example.mydairy;

public class Dairy {

    private String date;
    private String weather;
    private String place;
    private String text;
    private String pictureUri;
    private String musicUri;
    private String luyinUri;

    public Dairy(){}

    public Dairy(String date,String weather,String place,String text,String pictureUri,String musicUri,String luyinUri){
        this.date = date;
        this.weather = weather;
        this.place = place;
        this.text = text;
        this.pictureUri = pictureUri;
        this.musicUri = musicUri;
        this.luyinUri = luyinUri;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMusicUri(String musicUri) {
        this.musicUri = musicUri;
    }

    public void setLuyinUri(String luyinUri) {
        this.luyinUri = luyinUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getWeather() {
        return weather;
    }

    public String getMusicUri() {
        return musicUri;
    }

    public String getDate() {
        return date;
    }

    public String getLuyinUri() {
        return luyinUri;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public String getPlace() {
        return place;
    }

    public String getText() {
        return text;
    }

}
