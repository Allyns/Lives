package com.allyn.lives.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import me.yokeyword.indexablelistview.IndexEntity;

/**
 * Created by apple on 16/6/8.
 */
public class MusicBean extends IndexEntity {
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
    public static final String MusicBeamId = "musicId";

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int musicId;

    @Column("id")
    private int id;
    @Column("name")
    private String name;
    @Column("name")
    private String album;
    @Column("artist")
    private String artist;
    @Column("fileData")
    private String fileData;
    @Column("displayName")
    private String displayName;
    @Column("year")
    private String year;
    @Column("duration")
    private int duration;
    @Column("size")
    private long size;
    @Column("imageurl")
    private String imageurl;

    private boolean IsLike;

    public MusicBean() {
    }

    public MusicBean(int musicId, int id, String name, String album, String artist, String fileData, String displayName, String year, int duration, long size, String imageurl, boolean isLike) {
        this.musicId = musicId;
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
        IsLike = isLike;
    }

    public static String getMusicBeamId() {
        return MusicBeamId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public boolean isLike() {
        return IsLike;
    }

    public void setLike(boolean like) {
        IsLike = like;
    }
}
