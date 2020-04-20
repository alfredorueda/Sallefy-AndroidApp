package com.example.sallefy.controller.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sallefy.R;
import com.example.sallefy.controller.MusicPlayer;
import com.example.sallefy.controller.activities.PlayingSongActivity;
import com.example.sallefy.controller.adapters.OwnTrackListAdapter;
import com.example.sallefy.controller.adapters.TrackListAdapter;
import com.example.sallefy.controller.callbacks.TrackListAdapterCallback;
import com.example.sallefy.controller.restapi.callback.TrackCallback;
import com.example.sallefy.controller.restapi.manager.TrackManager;
import com.example.sallefy.model.Followed;
import com.example.sallefy.model.Liked;
import com.example.sallefy.model.Playlist;
import com.example.sallefy.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YLTracksFragment extends Fragment implements TrackCallback, TrackListAdapterCallback {
    public static final String TAG = YLTracksFragment.class.getName();

    public static YLTracksFragment getInstance() {
        return new YLTracksFragment();
    }

    private ImageButton addTrackButton;
    private RecyclerView recyclerView;
    private Playlist ownTracksPlaylist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_yl_tracks, container, false);

        addTrackButton = v.findViewById(R.id.include).findViewById(R.id.add_track_btn);
        addTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getParentFragment() != null;
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, CreateTrackFragment.getInstance())
                        .remove(getParentFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = v.findViewById(R.id.tracks_rv);

        TrackManager.getInstance(getContext()).getOwnTracks(YLTracksFragment.this);

        return v;
    }


    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(getContext(), R.string.exploded, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrackClick(Track track) {
        /*Intent intent = new Intent(getContext(), PlayingSongActivity.class);
        intent.putExtra("newTrack", true);
        intent.putExtra("track", track);
        intent.putExtra("playlist", ownTracksPlaylist);
        startActivity(intent);*/
        MusicPlayer.getInstance().onSetNextTrack(track, ownTracksPlaylist);
    }

    @Override
    public void onOptionsClick(Track track) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Fragment prev = getFragmentManager().findFragmentByTag(TrackOptionsFragment.TAG);
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up);
        transaction.addToBackStack(null);
        TrackOptionsFragment trackOptionsFragment = TrackOptionsFragment.getInstance(track);
        trackOptionsFragment.show(transaction, TrackOptionsFragment.TAG);
    }


    @Override
    public void onTracksReceived(List<Track> tracks) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        OwnTrackListAdapter adapter = new OwnTrackListAdapter((ArrayList<Track>) tracks, getContext(), YLTracksFragment.this, R.layout.item_track);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        ownTracksPlaylist = new Playlist();
        ownTracksPlaylist.setName(getContext().getString(R.string.own_tracks_playlist_name));
        ownTracksPlaylist.setTracks(tracks);
        for (int i = 0; i  < tracks.size(); i++ ){
            TrackManager.getInstance(getContext()).checkLiked(tracks.get(i), YLTracksFragment.this, i);
        }
        adapter.setOnItemClickListener(new OwnTrackListAdapter.OnItemClickListener() {

            @Override
            public void onLikeClick(Track track, int position) {
                TrackManager.getInstance(getContext()).likeTrack(track, YLTracksFragment.this, position);
            }

        });
    }

    @Override
    public void onNoTracks(Throwable throwable) {
        Toast.makeText(getContext(), R.string.error_getting_tracks, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrackLiked(int position) {
        ((OwnTrackListAdapter)recyclerView.getAdapter()).changeTrackLikeStateIcon(position);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onTrackLikedError(Throwable throwable) {
        Toast.makeText(getContext(), "Action failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrackLikedReceived(Liked liked, int position) {
        ((OwnTrackListAdapter)recyclerView.getAdapter()).updateTrackLikeStateIcon(position, liked.getLiked());
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCreateTrack() {
        // UNUSED
    }
}
