package nassekine.spartak.tarea06;


import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;


public class MainActivity extends AppCompatActivity {

    Button reproducir, reprodLocalServ;
    ExoPlayer player;
    String urlVideo = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    String urlVideoLocServ="http://192.168.1.8/tortugas.mp4";//no reproduce .avi
    String elecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reproducir =findViewById(R.id.btnReproducir);
        reprodLocalServ=findViewById(R.id.btpReprServLoc);

        //oculta la barra de arriba
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //oculta l abarra de navegacion
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        StyledPlayerView playerView = findViewById(R.id.playerView);
        player = new ExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(player);

        //player.setPlayWhenReady(true);
    }


    public void botonPlay(View view) {

       /* Button pulsado=new Button(this);
        pulsado=(Button) view;*/
        if(view.getId()==reproducir.getId()){
          elecion=urlVideo;
        }else{
            elecion=urlVideoLocServ;
        }

        MediaItem mediaItem = MediaItem.fromUri(elecion);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        return true;

    }
   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null) {
            outState.putLong("player_position", player.getCurrentPosition());
            outState.putBoolean("player_play_when_ready", player.getPlayWhenReady());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (player != null) {
            long playerPosition = savedInstanceState.getLong("player_position", 0);
            boolean playWhenReady = savedInstanceState.getBoolean("player_play_when_ready", true);
            player.seekTo(playerPosition);
            player.setPlayWhenReady(playWhenReady);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }*/
   /* @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //  orientación de la pantalla
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // modo paisaje
            setContentView(R.layout.activity_main);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // modo retrato
            setContentView(R.layout.activity_main_land);
        }
    }*/
}

