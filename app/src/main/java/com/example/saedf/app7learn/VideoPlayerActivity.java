package com.example.saedf.app7learn;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private Timer timer;
    private TextView videoCurrentDurationTextView;
    private SeekBar seekBar;

    private RelativeLayout.LayoutParams portRateLayoutParams;
    private RelativeLayout.LayoutParams landscapeLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        setupLayoutParams();
        setupVideo();
    }

    private void setupVideo() {
        videoView=findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse("http://clips.vorwaerts-gmbh.de/VfE_html5.mp4"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                setupViews();
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void setupViews() {
        final ImageView playButton=findViewById(R.id.iv_buttonPlay_video_activity);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.pause();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_play,null));
                }else {
                    videoView.start();
                    playButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_pause,null));
                }
            }
        });
        final ImageView forwardButton=findViewById(R.id.buttonForward_video_activity);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition()+5000);
            }
        });
        ImageView rewindButton=(ImageView)findViewById(R.id.buttonRewind_video_activity);
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition()-5000);
            }
        });
        TextView videoDurationTextView=findViewById(R.id.tv_video_duration);
        videoDurationTextView.setText(formatDuration(videoView.getDuration()));

        videoCurrentDurationTextView=(TextView)findViewById(R.id.tv_video_current_duration);
        videoCurrentDurationTextView.setText(formatDuration(0));

        seekBar=findViewById(R.id.seek_bar_activity_videoplayer);
        seekBar.setMax(videoView.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    videoView.seekTo(progress);
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
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        videoCurrentDurationTextView.setText(formatDuration(videoView.getCurrentPosition()));
                        seekBar.setProgress(videoView.getCurrentPosition());
                        seekBar.setSecondaryProgress((videoView.getBufferPercentage()*videoView.getDuration())/100);
                    }
                });
            }
        },0,1000);

    }

    private String formatDuration(long duration) {
        int seconds = (int) (duration / 1000);
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format(Locale.ENGLISH, "%02d", minutes) + ":" + String.format(Locale.ENGLISH, "%02d", seconds);
    }

    private void setupLayoutParams() {
        View toolbar=findViewById(R.id.toolbar_videoplayer_activity);
        View  mediaController=findViewById(R.id.layout_video_controller);

        landscapeLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        portRateLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        portRateLayoutParams.addRule(RelativeLayout.BELOW,toolbar.getId());
        portRateLayoutParams.addRule(RelativeLayout.ABOVE,mediaController.getId());

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FrameLayout frameLayout=findViewById(R.id.framelayout_root_video_activity);
        if (newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            frameLayout.setLayoutParams(portRateLayoutParams);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            frameLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.color_white));
        }else {
            frameLayout.setLayoutParams(landscapeLayoutParams);
            frameLayout.bringToFront();
            frameLayout.setBackgroundColor(ContextCompat.getColor(this,android.R.color.black));
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onDestroy() {
        if (timer!=null){
            timer.purge();
            timer.cancel();
        }
        super.onDestroy();
    }
}
