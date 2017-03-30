package com.example.nimrodkor.lunchbox.Util;

import com.facebook.AccessToken;

public class Util {
    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
