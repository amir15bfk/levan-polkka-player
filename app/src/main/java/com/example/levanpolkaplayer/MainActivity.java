package com.example.levanpolkaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer ;
    SeekBar seekBar;
    int fProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.SeekBar);
        seekBar.setMax(MediaPlayer.create(this,R.raw.levan_polkka).getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fProgress=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(fProgress);
            }
        });
        mythread my= new mythread();
        my.start();
    }

    class  mythread extends  Thread{
        public void run() {


            while(true){
                try {
                    Thread.sleep(1000);

                }  catch (Exception e) {}
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //seek bar   seekBar1.setProgress(mp .getCurrentPosition());
                        if (mediaPlayer !=null)
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                });



            }
        }}
    public void play(View v){
        if(mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.levan_polkka);
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this,"end of the song", LENGTH_LONG).show();
            }
        });
    }
    public void pause(View v){
        if (mediaPlayer != null)
        mediaPlayer.pause();
    }
    public void stop(View v){

        pausePlayer();
    }
    public void pausePlayer(){
        if (mediaPlayer != null){
        mediaPlayer.release();
        seekBar.setProgress(0);
        mediaPlayer=null;}
    }
}
