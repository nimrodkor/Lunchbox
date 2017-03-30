package com.example.nimrodkor.lunchbox;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.example.nimrodkor.lunchbox.Util.Util;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class MainActivity extends Activity {
    public static BehaviorSubject<Profile> profileStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileStream = BehaviorSubject.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        getFragmentManager().beginTransaction().add(R.id.top_panel, new UserFragment(), "user").commit();
        getFragmentManager().beginTransaction().add(R.id.main_window, new UserLoginFragment()).commit();
        setProfileListener();
    }

    private void setProfileListener() {
        getProfile().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    if (Util.isLoggedIn())
                        onLogin();
                    else
                        onLogout();
                });
        if (Util.isLoggedIn())
            profileStream.onNext(Profile.getCurrentProfile());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.main_window);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    public void onLogin() {
        getFragmentManager().beginTransaction().replace(R.id.main_window, new CreateLunchBoxFragment()).commit();
    }

    public void onLogout() {
        UserLoginFragment loginFragment = new UserLoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.main_window, loginFragment).commit();
    }

    public void setProfile(Profile prof) {
        profileStream.onNext(prof);
    }

    public Observable<Profile> getProfile(){
        return profileStream;
    }
}
