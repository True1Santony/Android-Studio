package nassekine.spartak.tarea06;


import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;


public class MainActivity extends AppCompatActivity {


    ExoPlayer player;
    String urlVideo = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    String urlVideoRemoro="https://MiNas-221.eu.tnas.link/tos/#/share?share_link=CAIYCNKxi%2Bz4jdD0%2B9tnpc19H%2BL2mUkL332oOjRk%2F1QFtiA%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //oculta la barra de arriba
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //oculta l abarra de navegacion
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        StyledPlayerView playerView = findViewById(R.id.playerView);
        player = new ExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(urlVideoRemoro);
        player.setMediaItem(mediaItem);
        player.prepare();
        //player.setPlayWhenReady(true);
    }


    public void botonPlay(View view) {


        player.setPlayWhenReady(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        return true;

    }
}
