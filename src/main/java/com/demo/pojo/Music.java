package com.demo.pojo;

public class Music {
    private String musicId;

    private String name;

    private String author;

    private String publisher;

    private Double time;

    private String src;

    private String lyricSrc;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId == null ? null : musicId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getLyricSrc() {
        return lyricSrc;
    }

    public void setLyricSrc(String lyricSrc) {
        this.lyricSrc = lyricSrc == null ? null : lyricSrc.trim();
    }
}