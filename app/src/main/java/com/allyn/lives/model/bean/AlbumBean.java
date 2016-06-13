package com.allyn.lives.model.bean;

/**
 * Created by apple on 16/6/12.
 */
public class AlbumBean {

    private int id;
    private String name;
    private int size;
    private String Singer;
    private String imageUrl;

    public AlbumBean() {

    }

    public AlbumBean(int id, String name, int size, String singer, String imageUrl) {
        this.id = id;
        this.name = name;
        this.size = size;
        Singer = singer;
        this.imageUrl = imageUrl;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
