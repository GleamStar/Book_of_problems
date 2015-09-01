package artinbyte.alex.BookOfproblems;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Music2 extends Service {
    public Music2() {
    }

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this,R.raw.zadachi );
        player.setLooping(true);
        startService();

    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    private void startService() {
        player.start();
    }
}
