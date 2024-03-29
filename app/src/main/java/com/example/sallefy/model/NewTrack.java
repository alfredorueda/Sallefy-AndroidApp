package com.example.sallefy.model;

import com.example.sallefy.objectbox.converters.GenreListConverter;
import com.example.sallefy.objectbox.converters.UserConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.objectbox.annotation.Convert;

public class NewTrack implements Serializable {

    @SerializedName("color")
    private String color;

    @SerializedName("duration")
    private Integer duration;

    @SerializedName("genres")
    @Convert(converter = GenreListConverter.class, dbType = String.class)
    private List<Genre> genres = null;

    @SerializedName("name")
    private String name;

    @SerializedName("owner")
    @Convert(converter = UserConverter.class, dbType = String.class)
    private User user;

    @SerializedName("released")
    private String released;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("url")
    private String url;

    @SerializedName("id")
    private Long id = null;

    private boolean selected = false;

    private boolean liked;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserLogin() {
        return user.getLogin();
    }

    public void setUserLogin(String userLogin) {
        user.setLogin(userLogin);
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}