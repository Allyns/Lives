package com.allyn.lives.bean;

import me.yokeyword.indexablelistview.IndexEntity;

/**
 * Created by apple on 16/6/8.
 */
public class MusicBean extends IndexEntity{
    /***
     * 歌曲id   id
     * 歌曲名称     name
     * 专辑名     album
     * 歌手名      artist
     * 文件全路径     data
     * 歌曲文件名称     displayNmae
     * 文件发行日期     year
     * 歌曲总数时长    duration
     * 歌曲总大小    size
     */
    private int id;
    private String name;
    private String album;
    private String artist;
    private String fileData;
    private String displayName;
    private String year;
    private int duration;
    private long size;
    private String imageurl;

    public MusicBean() {
    }

    public MusicBean(int id, String name, String album, String artist, String fileData, String displayName, String year, int duration, long size, String imageurl) {
        this.id = id;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.fileData = fileData;
        this.displayName = displayName;
        this.year = year;
        this.duration = duration;
        this.size = size;
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
