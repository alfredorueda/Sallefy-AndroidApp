package com.example.sallefy.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {

    @SerializedName("cover")
    private String cover;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("publicAccessible")
    private Boolean publicAccessible;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("owner")
    private User user;

    @SerializedName("liked")
    private boolean liked;

    @SerializedName("tracks")
    private List<Track> tracks = null;

    private boolean followed;
    @Nullable
    private Object obj;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublicAccessible() {
        return publicAccessible;
    }

    public void setPublicAccessible(Boolean publicAccessible) {
        this.publicAccessible = publicAccessible;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
        if (user == null) {
            user = new User();
        }
        user.setLogin(userLogin);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj != null;
        if (getClass() != obj.getClass())
            return false;

        return ((Playlist) obj).getId().equals(this.id) && ((Playlist) obj).getName().equals(this.name);
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }
}
