package br.com.impacta.curso.prj_012_thread;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by rcaru on 17/03/2018.
 */

public class TocadorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mplayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
        mplayer.setLooping(true);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mplayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mplayer.stop();
        mplayer.release();

        super.onDestroy();
    }

    private MediaPlayer mplayer;
}
