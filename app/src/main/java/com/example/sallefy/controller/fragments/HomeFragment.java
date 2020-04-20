package com.example.sallefy.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sallefy.R;
import com.example.sallefy.controller.activities.PlayingSongActivity;
import com.example.sallefy.controller.adapters.PlaylistGroupListAdapter;
import com.example.sallefy.controller.callbacks.PlaylistAdapterCallback;
import com.example.sallefy.controller.restapi.callback.PlaylistCallback;
import com.example.sallefy.controller.restapi.manager.PlaylistManager;
import com.example.sallefy.model.Playlist;
import com.example.sallefy.model.PlaylistGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements PlaylistAdapterCallback, PlaylistCallback {

    public static final String TAG = HomeFragment.class.getName();

    private final static int POPULAR_PLAYLISTS = 0;
    private final static int DISCOVER_PLAYLISTS = 1;
    private final static int LIKED_PLAYLISTS = 2;


    private ArrayList<PlaylistGroup> mPlaylistGroups;
    private RecyclerView rvPlaylistGroups;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlaylistGroups = new ArrayList<>();
        mPlaylistGroups.add(new PlaylistGroup(getContext().getString(R.string.popular_playlists), null));
        mPlaylistGroups.add(new PlaylistGroup(getContext().getString(R.string.discover_playlists), null));
        mPlaylistGroups.add(new PlaylistGroup(getContext().getString(R.string.liked_playlists), null));

        PlaylistManager.getInstance(getContext()).getAllPlaylistsByMostFollowed(HomeFragment.this);
        PlaylistManager.getInstance(getContext()).getAllPlaylistsByMostRecent(HomeFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvPlaylistGroups = v.findViewById(R.id.playlist_groups_rv);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvPlaylistGroups.setLayoutManager(manager);
        PlaylistGroupListAdapter adapter = new PlaylistGroupListAdapter(getContext(), mPlaylistGroups, HomeFragment.this);
        rvPlaylistGroups.setAdapter(adapter);

        return v;
    }

    private PlaylistGroup getPlaylistGroupByName(String name) {
        for (PlaylistGroup playlistGroup : mPlaylistGroups){
            if (playlistGroup.getGroupName().equals(name)) {
                return playlistGroup;
            }
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPlaylistClick(Playlist playlist) {

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PlaylistFragment.getInstance(playlist), PlaylistFragment.TAG)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onPlaylistCreated(Playlist playlist) {
        // UNUSED
    }

    @Override
    public void onPlaylistFailure(Throwable throwable) {
        // UNUSED
    }

    @Override
    public void onPlaylistReceived(Playlist playlist) {
        // UNUSED
    }

    @Override
    public void onPlaylistNotReceived(Throwable throwable) {
        // UNUSED
    }

    @Override
    public void onPlaylistUpdated(Playlist playlist) {
        // UNUSED
    }

    @Override
    public void onPlaylistNotUpdated(Throwable throwable) {
        // UNUSED
    }

    @Override
    public void onPlaylistsReceived(List<Playlist> playlists) {
        // UNUSED
    }

    @Override
    public void onPlaylistsNotReceived(Throwable throwable) {
        Toast.makeText(getContext(), R.string.error_getting_playlists, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMostRecentPlaylistsReceived(List<Playlist> playlists) {
        mPlaylistGroups.get(DISCOVER_PLAYLISTS).setPlaylists((ArrayList) playlists);

        ArrayList<Playlist> likedPlaylists = new ArrayList<>();

        for (Playlist p : playlists) {
            if (p.isLiked()) {
                likedPlaylists.add(p);
            }
        }

        mPlaylistGroups.get(LIKED_PLAYLISTS).setPlaylists(likedPlaylists);

        PlaylistGroupListAdapter adapter = new PlaylistGroupListAdapter(getContext(), mPlaylistGroups, HomeFragment.this);
        rvPlaylistGroups.setAdapter(adapter);
    }

    @Override
    public void onMostFollowedPlaylistsReceived(List<Playlist> playlists) {
        mPlaylistGroups.get(POPULAR_PLAYLISTS).setPlaylists((ArrayList) playlists);

        PlaylistGroupListAdapter adapter = new PlaylistGroupListAdapter(getContext(), mPlaylistGroups, HomeFragment.this);
        rvPlaylistGroups.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(getContext(), R.string.exploded, Toast.LENGTH_LONG).show();
    }
}
