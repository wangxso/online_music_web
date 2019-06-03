package com.demo.pojo;

public class ListenList {
    private Integer listenListId;

    private String userId;

    private String musicId;

    public Integer getListenListId() {
        return listenListId;
    }

    public void setListenListId(Integer listenListId) {
        this.listenListId = listenListId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId == null ? null : musicId.trim();
    }
}