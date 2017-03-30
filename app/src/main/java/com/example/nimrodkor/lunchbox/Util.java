package com.example.nimrodkor.lunchbox;

import com.facebook.AccessToken;

class Util {
    static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
