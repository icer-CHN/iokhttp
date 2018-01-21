package com.icer.iocoin;

import android.app.Application;
import android.media.MediaPlayer;


public class App extends Application {

    private static App sApp;

    public MediaPlayer mMediaPlayerUp;
    public MediaPlayer mMediaPlayerDown;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mMediaPlayerUp = MediaPlayer.create(this, R.raw.sound_up);
        mMediaPlayerDown = MediaPlayer.create(this, R.raw.sound_down);
    }

    public static App getInstance() {
        return sApp;
    }
}
