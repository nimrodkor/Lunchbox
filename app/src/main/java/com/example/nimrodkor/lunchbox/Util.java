package com.example.nimrodkor.lunchbox;

import com.facebook.AccessToken;

/**
 * Created by nimrodkor on 3/30/17.
 */

class Util {
    static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
