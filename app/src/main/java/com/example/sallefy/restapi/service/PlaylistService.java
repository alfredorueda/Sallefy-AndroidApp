package com.example.sallefy.restapi.service;

import com.example.sallefy_ac1.model.Playlist;

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
    Call<Playlist> getPlaylistById(@Path("id") String id, @Header("Authorization") String token);

    @PUT("playlists")
    Call<Playlist> updatePlaylist(@Body Playlist playlist, @Header("Authorization") String token);
}
