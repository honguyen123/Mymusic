package com.example.mymusic;

import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

        TextView txtTitle,txtTimeSong,txtTimeTotal;
        SeekBar sksong;
        ImageButton btnprev,btnPlay,btnStop,btnNext;


        ArrayList<Song> arraySong;
        int position = 0;
        MediaPlayer mediaPlayer;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AnhXa();
        AddSong();
        KhoitaoMediaPlayer();
        setTimeTotal();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                position++;
                if(position>arraySong.size()-1){
                    position=0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.stop);
                setTimeTotal();
                UpdateTimeSong();
            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                position--;
                if(position<0){
                    position=arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.stop);
                setTimeTotal();
                UpdateTimeSong();

            }


        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                KhoitaoMediaPlayer();
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    //nếu đang hát -> pause -> đổi hình play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }else {
                    // Đang dừng -> phát -> đổi hình pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.stop);
                }
                setTimeTotal();
                UpdateTimeSong();
            }

        });
        sksong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sksong.getProgress());
            }
        });
    }
    private void UpdateTimeSong(){
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio =new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                sksong.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        },100);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeTotal(){
        SimpleDateFormat dinhDangGio =new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        //gán max của sksong= mediaplayer.getduration()
        sksong.setMax(mediaPlayer.getDuration());
    }
    private void KhoitaoMediaPlayer(){
        mediaPlayer=MediaPlayer.create(Main2Activity.this,arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
    }
    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Một Năm Mới Bình An",R.raw.mot));
        arraySong.add(new Song("Như Hoa Mùa Xuân",R.raw.nhu));
    }
    private void   AnhXa(){
        txtTimeSong     =(TextView) findViewById(R.id.textviewTimesong);
        txtTimeTotal    =(TextView) findViewById(R.id.textviewTimetotal);
        txtTitle        =(TextView) findViewById(R.id.textviewTitle);
        sksong          =(SeekBar) findViewById(R.id.seekbarsong);
        btnNext         =(ImageButton) findViewById(R.id.imageButtonnext);
        btnPlay         =(ImageButton) findViewById(R.id.imageButtonplay);
        btnprev         =(ImageButton) findViewById(R.id.imageButtonprev);
        btnStop         =(ImageButton) findViewById(R.id.imageButtonstop);
        }
}
