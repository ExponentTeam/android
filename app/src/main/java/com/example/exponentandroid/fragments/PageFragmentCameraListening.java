package com.example.exponentandroid.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.exponentandroid.R;

public class PageFragmentCameraListening extends Fragment {
    private VideoView cameraStream;
    private ImageButton imgButtonPlay;
    private TextView currentTime;
    private TextView durationTime;
    private TextView testText;
    private Uri videoUri;
    private ProgressBar progressBuffering;
    public boolean isPlaying = false;
    private int currentTimeSec = 0;
    private int durationTimeSec = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup inflate = (ViewGroup) inflater.inflate(R.layout.cameras_listening, container, false);
        imgButtonPlay =  inflate.findViewById(R.id.playButton);
        currentTime = inflate.findViewById(R.id.currentTime);
        durationTime = inflate.findViewById(R.id.durationTime);
        testText = inflate.findViewById(R.id.textView8);
        cameraStream = inflate.findViewById(R.id.videoView);
        progressBuffering = inflate.findViewById(R.id.progressBuffering);

        videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/exponent-f2739.appspot.com/o/yt1s.com%20-%20Oh%20shit%20Im%20sorry%20%D0%BD%D0%BE%20%D0%B2%208K%2060FPS.mp4?alt=media&token=2b0fce4f-b334-49d8-bf0c-e588f237753b");
        cameraStream.setVideoURI(videoUri);
        cameraStream.requestFocus();
        cameraStream.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                testText.setText("at least i work");
                durationTimeSec = mp.getDuration()/1000;
                String durationString = String.format("%02d:%02d", durationTimeSec / 60, durationTimeSec % 60);
                durationTime.setText(durationString);
            }
        });
        cameraStream.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if(what == mp.MEDIA_INFO_BUFFERING_START){
                    progressBuffering.setVisibility(View.VISIBLE);
                    testText.setText("Buffer Visible");
                } else if(what == mp.MEDIA_INFO_BUFFERING_END){
                    progressBuffering.setVisibility(View.INVISIBLE);
                    testText.setText("Buffer Invisible");
                }
                return false;
            }
        });



        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPlaying) {
                    testText.setText("Hf,jnf'");
                    cameraStream.start();
                    isPlaying = true;
                    imgButtonPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                }
                else {
                    cameraStream.pause();
                    isPlaying = false;
                    imgButtonPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }

            }
        });

        cameraStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgButtonPlay.performClick();
            }
        });
        return inflate;
    }
}
