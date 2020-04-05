package com.example.sallefy.controller.restapi.manager;

import android.content.Context;
import android.util.Log;

import com.example.sallefy.model.Track;
import com.example.sallefy.model.UserToken;
import com.example.sallefy.controller.restapi.callback.TrackCallback;
import com.example.sallefy.controller.restapi.service.TrackService;
import com.example.sallefy.utils.Constants;
import com.example.sallefy.utils.Session;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrackManager {

    private static final String TAG = "TrackManager";
    private Context mContext;
    private static TrackManager sTrackManager;
    private Retrofit mRetrofit;
    private TrackService mTrackService;


    public static TrackManager getInstance (Context context) {
        if (sTrackManager == null) {
            sTrackManager = new TrackManager(context);
        }

        return sTrackManager;
    }

    public TrackManager(Context context) {
        mContext = context;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.NETWORK.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTrackService = mRetrofit.create(TrackService.class);
    }

    public synchronized void getAllTracks(final TrackCallback trackCallback) {
        UserToken userToken = Session.getInstance(mContext).getUserToken();

        Call<List<Track>> call = mTrackService.getAllTracks( "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.isSuccessful()) {
                    trackCallback.onTracksReceived(response.body());
                } else {
                    try {
                        trackCallback.onNoTracks(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                trackCallback.onNoTracks(t);
            }
        });
    }

    public synchronized void getOwnTracks(final TrackCallback trackCallback) {
        UserToken userToken = Session.getInstance(mContext).getUserToken();

        Call<List<Track>> call = mTrackService.getOwnTracks( "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.isSuccessful()) {
                    trackCallback.onTracksReceived(response.body());
                } else {
                    try {
                        trackCallback.onNoTracks(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                trackCallback.onNoTracks(t);
            }
        });
    }
}
