package com.example.amanmj.exoplayer_temp;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity
{
    public static ExoPlayer exo;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //String url="http://dl.enjoypur.vc/upload_file/5570/6757/%20Bollywood%20Hindi%20Mp3%20Songs%202015/Hate%20Story%203%20(2015)%20Mp3%20Songs/03%20Wajah%20Tum%20Ho%20-%20Hate%20Story%203%20%28Armaan%20Malik%29%20190Kbps.mp3";

        //exoplayer code
        exo= ExoPlayer.Factory.newInstance(1);
        //mySource m=new mySource();
        //m.execute(url);
        //DataSource dataSource = new AssetDataSource(getApplicationContext());
        //ExtractorSampleSource extractorSampleSource = new ExtractorSampleSource(Uri.parse(url), dataSource, new DefaultAllocator(64 * 1024), 64 * 1024 * 256);
        //File f=new File(Environment.getExternalStorageDirectory(),"song.mp3");

        /*try {
            FileInputStream fileInputStream=new FileInputStream(f);
            FileDescriptor fd=fileInputStream.getFD();
            SampleSource sampleSource=new FrameworkSampleSource(fd,0,f.length());
            TrackRenderer audio = new MediaCodecAudioTrackRenderer(sampleSource, null, true);
            exo.prepare(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //TrackRenderer audio = new MediaCodecAudioTrackRenderer(extractorSampleSource, null, true);
        //exo.prepare(audio);
        file=new File(Environment.getExternalStorageDirectory(),"song.mp3");
        if(file.exists()==false)
        {
           Log.i("Aman","file exists");
        }
        DataSource dataSource=new myDataSource(file.length());
        ExtractorSampleSource extractorSampleSource=new ExtractorSampleSource(Uri.parse("song.mp3"),dataSource,new DefaultAllocator(64*1024),64*1024*256);
        TrackRenderer audio=new MediaCodecAudioTrackRenderer(extractorSampleSource,null,true);
        Log.i("Aman","reached end");
        Log.i("Aman","length of file= "+file.length());
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exo.prepare(audio);
        exo.setPlayWhenReady(true);
        Log.i("Aman","exoplayer length="+exo.getDuration());

    }
    /*@Override
    public long getAvailableBytes() {
        return file.length();
    }*/
}
