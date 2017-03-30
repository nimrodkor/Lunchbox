package com.example.nimrodkor.lunchbox;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getFragmentManager().beginTransaction().add(R.id.top_panel, new UserFragment(), "user").commit();
        getFragmentManager().beginTransaction().add(R.id.main_window, new UserLoginFragment()).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLoggedIn())
            onLogin(Profile.getCurrentProfile().getName(), Profile.getCurrentProfile().getId());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.main_window);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    public void onLogin(String name, String id) {
        UserFragment fragment = (UserFragment) getFragmentManager().findFragmentById(R.id.top_panel);
        fragment.setUser(name, id);
        getFragmentManager().beginTransaction().replace(R.id.main_window, new CreateLunchBoxFragment()).commit();
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
