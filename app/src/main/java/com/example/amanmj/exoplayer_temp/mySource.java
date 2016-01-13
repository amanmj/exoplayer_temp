package com.example.amanmj.exoplayer_temp;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by amanmj on 10/1/16.
 */
public class mySource extends AsyncTask<String,String,String>
{
    @Override
    protected String doInBackground(String... params)
    {
        try
        {
            //ExoPlayer exo = MainActivity.exo;
            String url=params[0];
            Log.i("Aman",url);
            File f=new File(Environment.getExternalStorageDirectory(),"asong.mp3");
            URL u = new URL(url);
            Log.i("Aman", "hoho");
            f.createNewFile();
            Log.i("Aman", "hoho");
            URLConnection urlConnection=u.openConnection();

            Log.i("Aman","hoho");
            InputStream in=urlConnection.getInputStream();
            FileOutputStream fileOutputStream=new FileOutputStream(f);
            Log.i("Aman","Downlaoding");
            byte[] buf = new byte[10000];
            while (true) {
                int len = in.read(buf);
                if (len == -1) {
                    break;
                }
                fileOutputStream.write(buf, 0, len);
            }
            Log.i("Aman ","Downloaded");
            in.close();
            fileOutputStream.flush();
            fileOutputStream.close();


            Log.i("Aman ","length of file = "+f.length());
           // FileInputStream fileInputStream=new FileInputStream(file);
            //FileDescriptor fd=fileInputStream.getFD();
            //SampleSource sampleSource=new FrameworkSampleSource(this,uri,null);
            //TrackRenderer audio = new MediaCodecAudioTrackRenderer(sampleSource, null, true);
            //exo.prepare(audio);
            //exo.setPlayWhenReady(true);
            return url;
        }
        catch (Exception e)
        {
            Log.i("Aman exception","exception");
            e.printStackTrace();
        }
        return null;
    }

}
