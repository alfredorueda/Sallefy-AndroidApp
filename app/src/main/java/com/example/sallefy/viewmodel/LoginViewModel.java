package com.example.sallefy.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.sallefy.model.Playlist;
import com.example.sallefy.model.Track;
import com.example.sallefy.model.User;
import com.example.sallefy.network.SallefyRepository;
import com.example.sallefy.network.callback.GetUserCallback;
import com.example.sallefy.network.callback.LoginCallback;
import com.example.sallefy.auth.Session;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private SallefyRepository sallefyRepository;
    private boolean rememberPreferences;

    @Inject
    public LoginViewModel(SallefyRepository sallefyRepository) {
        this.sallefyRepository = sallefyRepository;
        rememberPreferences = false;
    }

    public void login(String username, String password, LoginCallback callback) {
        sallefyRepository.loginAttempt(username, password, callback);
    }

    public void setRememberPreferences(boolean rememberPreferences) {
        this.rememberPreferences = rememberPreferences;
    }

    public boolean getRememberPreferences() {
        return rememberPreferences;
    }

    public void saveUser(String username) {
        sallefyRepository.getUserById(username, new GetUserCallback() {
            @Override
            public void onUserReceived(User user) {
                Session.setUser(user);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    public Track getSharedTrack() {
        // TODO: IMPLEMENT
    }

    public Playlist getSharedPlaylist() {
        // TODO: IMPLEMENT
    }

    public User getSharedUser() {
        // TODO: IMPLEMENT
    }
}
