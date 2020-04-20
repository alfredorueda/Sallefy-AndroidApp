package com.example.sallefy.controller.restapi.service;

import com.example.sallefy.model.Followed;
import com.example.sallefy.model.Playlist;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlaylistService {
    @POST("playlists")
    Call<Playlist> createPlaylist(@Body Playlist playlist, @Header("Authorization") String token);

    @GET("playlists/{id}")
    Call<Playlist> getPlaylistById(@Path("id") Integer id, @Header("Authorization") String token);

    @GET("me/playlists")
    Call<List<Playlist>> getOwnPlaylists(@Header("Authorization") String token);

    @GET("playlists?recent=true")
    Call<List<Playlist>> getAllPlaylistsByMostRecent(@Header("Authorization") String token);

    @GET("playlists?popular=true")
    Call<List<Playlist>> getAllPlaylistsByMostFollowed(@Header("Authorization") String token);

    @PUT("playlists")
    Call<Playlist> updatePlaylist(@Body Playlist playlist, @Header("Authorization") String token);

    @PUT("playlists/{id}/follow")
    Call<ResponseBody> followPlaylist(@Path("id") String id, @Header("Authorization") String token);

    @GET("playlists/{id}/follow")
    Call<Followed> isFollowed(@Path("id") String id, @Header("Authorization") String token);
}
