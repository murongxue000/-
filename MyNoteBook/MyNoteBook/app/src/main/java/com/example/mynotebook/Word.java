package com.example.mynotebook;

public class Word {

    private String word;
    private String meaning;
    private String sentence;
    private String yORn;

    public Word(){}

    public Word(String word, String meaning, String sentence,String yORn) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.yORn = yORn;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public String getyORn() {return yORn;}

    public String getWord() {
        return word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setyORn(String yORn) {this.yORn = yORn;}
}
