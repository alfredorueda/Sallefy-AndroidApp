package com.example.sallefy.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sallefy.R;
import com.example.sallefy.callback.PlaylistAdapterCallback;
import com.example.sallefy.model.Playlist;

import java.util.ArrayList;

public class SearchPlaylistListAdapter extends RecyclerView.Adapter<SearchPlaylistListAdapter.ViewHolder> {

    private static final String TAG = "TrackListAdapter";
    private ArrayList<Playlist> mPlaylists;
    private Context mContext;
    private PlaylistAdapterCallback mCallback;

    public SearchPlaylistListAdapter(Context context, ArrayList<Playlist> playlists, PlaylistAdapterCallback callback) {
        mContext = context;
        mPlaylists = playlists;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new SearchPlaylistListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (mPlaylists != null && mPlaylists.size() > 0) {
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null)
                        mCallback.onPlaylistClick(mPlaylists.get(position));
                }
            });
            holder.mTitle.setText(mPlaylists.get(position).getName());
            holder.mAuthor.setText(mPlaylists.get(position).getUser().getLogin());
            if (mPlaylists.get(position).getThumbnail() != null) {
                Glide.with(mContext)
                        .asBitmap()
                        .placeholder(R.drawable.ic_audiotrack_60dp)
                        .load(mPlaylists.get(position).getThumbnail())
                        .into(holder.mPhoto);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPlaylists != null ? mPlaylists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mLayout;
        ImageView mPhoto;
        TextView mTitle;
        TextView mAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.item_playlist_layout);
            mPhoto = itemView.findViewById(R.id.main_track_image);
            mTitle = itemView.findViewById(R.id.main_track_title);
            mAuthor = itemView.findViewById(R.id.main_author_title);
        }
    }
}
