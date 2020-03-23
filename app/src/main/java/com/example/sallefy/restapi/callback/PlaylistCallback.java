package com.example.sallefy.restapi.callback;

import com.example.sallefy.model.Playlist;

public interface PlaylistCallback extends FailureCallback {
    void onPlaylistCreated(Playlist playlist);
    void onPlaylistFailure(Throwable throwable);
    void onPlaylistReceived(Playlist playlist);
    void onPlaylistNotReceived(Throwable throwable);
    void onPlaylistUpdated(Playlist playlist);
    void onPlaylistNotUpdated(Throwable throwable);
}
