package com.example.saedf.app7learn;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private TextView currentDurationTextview;
    private SeekBar seekBar;
    private Timer timer;
    private ImageView playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        setupMediaPlayer();
    }

    private void setupMediaPlayer() {
        File musicDirectory = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File music = new File(musicDirectory, "invite_kakoband.mp3");
        if (music.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(this, Uri.fromFile(music));
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        setupViews();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_play, null));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Music Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViews() {
        playButton = findViewById(R.id.iv_button_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_play, null));
                }else {
                    mediaPlayer.start();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_pause,null));

                }
            }
        });
        TextView duration=findViewById(R.id.text_music_duration);
        duration.setText(formatDuration(mediaPlayer.getDuration()));
        currentDurationTextview=findViewById(R.id.text_music_current_duration);
        currentDurationTextview.setText(formatDuration(0));

        ImageView forwardButton=findViewById(R.id.iv_button_forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });

        ImageView rewindButton=findViewById(R.id.iv_button_rewind);
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
            }
        });

        seekBar=findViewById(R.id.seek_bar_activity_musicPlayer);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        timer=new Timer();
        timer.schedule(new MainTimer(),0,1000);

    }

    private String formatDuration(long duration) {
        int seconds= (int) (duration/1000);
        int minutes=seconds/60;
        seconds%=60;
        return String.format(Locale.ENGLISH,"%02d",minutes)+":"+
                String.format(Locale.ENGLISH,"%02d",seconds);
    }

    private class MainTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentDurationTextview.setText(formatDuration(mediaPlayer.getCurrentPosition()));
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        timer.purge();
        timer.cancel();
        super.onDestroy();
    }
}
