package com.example.nimrodkor.lunchbox;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UserFragment extends Fragment {
    @BindView(R.id.welcome_user) TextView mWelcome;
    @BindView(R.id.logout_button) Button mLogoutButton;
    @BindView(R.id.profile_pic) ProfilePictureView mProfilePic;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        mLogoutButton.setVisibility(View.GONE);
        mProfilePic.setVisibility(View.GONE);
        listenToProfile();
        return view;
    }

    private void listenToProfile() {
        ((MainActivity) getActivity()).getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleProfileChange);
    }

    private void handleProfileChange(Profile profile) {
        String name;
        String id;
        if (profile != null) {
            id = profile.getId();
            name = profile.getName();
            mWelcome.setText(String.format("%s", name));
            mProfilePic.setProfileId(id);
            mProfilePic.setVisibility(View.VISIBLE);
            mLogoutButton.setVisibility(View.VISIBLE);
        } else {
            mProfilePic.setVisibility(View.GONE);
            mLogoutButton.setVisibility(View.GONE);
            mWelcome.setText(R.string.welcome_guest);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.logout_button)
    public void logout() {
        LoginManager.getInstance().logOut();
        ((MainActivity) getActivity()).setProfile(null);
    }
}
