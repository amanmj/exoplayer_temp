package com.example.amanmj.exoplayer_temp;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.AssetDataSource;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;

import java.io.FileDescriptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //exoplayer code

        DataSource dataSource = new AssetDataSource(getApplicationContext());
        ExtractorSampleSource extractorSampleSource = new ExtractorSampleSource(Uri.parse("asset:///song.mp3"), dataSource, new DefaultAllocator(1000), 5000);
        ExoPlayer exo = ExoPlayer.Factory.newInstance(1);
        TrackRenderer audio = new MediaCodecAudioTrackRenderer(extractorSampleSource, null, true);
        exo.prepare(audio);
        exo.setPlayWhenReady(true);
    }
}
