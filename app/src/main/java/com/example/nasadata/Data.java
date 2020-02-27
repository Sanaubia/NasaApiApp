package com.example.nasadata;


public class Data {
    private String quotes, author;
    private String image;
    public Data(){

    }
    public Data(String quotes,String author,String image){
        this.quotes = quotes;
        this.author = author;
        this.image = image;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}