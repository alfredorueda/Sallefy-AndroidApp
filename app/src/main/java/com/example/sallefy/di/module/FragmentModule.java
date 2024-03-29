package com.example.sallefy.di.module;

import com.example.sallefy.fragment.AddSongToPlaylistFragment;
import com.example.sallefy.fragment.AddTrackToPlaylistFragment;
import com.example.sallefy.fragment.CreatePlaylistFragment;
import com.example.sallefy.fragment.CreateTrackFragment;
import com.example.sallefy.fragment.HomeFragment;
import com.example.sallefy.fragment.LoginFragment;
import com.example.sallefy.fragment.OptionsFragment;
import com.example.sallefy.fragment.PlayingSongFragment;
import com.example.sallefy.fragment.PlaylistFragment;
import com.example.sallefy.fragment.ProfileFragment;
import com.example.sallefy.fragment.ProfilePlaylistsFragment;
import com.example.sallefy.fragment.ProfileTopTracksFragment;
import com.example.sallefy.fragment.ProfileTracksFragment;
import com.example.sallefy.fragment.RegisterFragment;
import com.example.sallefy.fragment.SearchFragment;
import com.example.sallefy.fragment.StatisticsFragment;
import com.example.sallefy.fragment.TrackOptionsFragment;
import com.example.sallefy.fragment.TrackStatisticsFragment;
import com.example.sallefy.fragment.UploadProfileImageFragment;
import com.example.sallefy.fragment.YourLibraryFollowersFragment;
import com.example.sallefy.fragment.YourLibraryFollowingsFragment;
import com.example.sallefy.fragment.YourLibraryFragment;
import com.example.sallefy.fragment.YourLibraryPlaylistsFragment;
import com.example.sallefy.fragment.YourLibraryTracksFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract RegisterFragment registerFragment();

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract PlayingSongFragment playingSongFragment();

    @ContributesAndroidInjector
    abstract AddSongToPlaylistFragment addSongToPlaylistFragment();

    @ContributesAndroidInjector
    abstract AddTrackToPlaylistFragment addTrackToPlaylistFragment();

    @ContributesAndroidInjector
    abstract CreateTrackFragment createTrackFragment();

    @ContributesAndroidInjector
    abstract OptionsFragment optionsFragment();

    @ContributesAndroidInjector
    abstract PlaylistFragment playlistFragment();

    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector
    abstract TrackOptionsFragment trackOptionsFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    abstract ProfilePlaylistsFragment userPlaylistsFragment();

    @ContributesAndroidInjector
    abstract ProfileTracksFragment userTracksFragment();

    @ContributesAndroidInjector
    abstract YourLibraryFragment yourLibraryFragment();

    @ContributesAndroidInjector
    abstract YourLibraryFollowersFragment yourLibraryFollowersFragment();

    @ContributesAndroidInjector
    abstract YourLibraryFollowingsFragment yourLibraryFollowingsFragment();

    @ContributesAndroidInjector
    abstract YourLibraryPlaylistsFragment yourLibraryPlaylistsFragment();

    @ContributesAndroidInjector
    abstract YourLibraryTracksFragment yourLibraryTracksFragment();

    @ContributesAndroidInjector
    abstract CreatePlaylistFragment createPlaylistFragment();

    @ContributesAndroidInjector
    abstract UploadProfileImageFragment uploadProfileImageFragment();

    @ContributesAndroidInjector
    abstract StatisticsFragment statisticsFragment();

    @ContributesAndroidInjector
    abstract ProfileTopTracksFragment profileTopTracksFragment();

    @ContributesAndroidInjector
    abstract TrackStatisticsFragment trackStatisticsFragment();
}
