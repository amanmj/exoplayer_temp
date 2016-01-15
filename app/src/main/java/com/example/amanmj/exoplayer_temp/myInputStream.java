package com.example.amanmj.exoplayer_temp;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by adminReach on 09/01/16.
 */
public class myInputStream extends InputStream {

    private long getAvailableBytes;
    //private final GetAvailableBytes getAvailableBytes;
    private final RandomAccessFile randomAccessFile;

    public myInputStream(long getAvailableBytes, RandomAccessFile randomAccessFile) {
        this.getAvailableBytes = getAvailableBytes;
        this.randomAccessFile = randomAccessFile;
    }

    @Override
    public int available() throws IOException
    {
        long n = this.getAvailableBytes-randomAccessFile.getFilePointer();

        while(n<=0)
        {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {

                e.printStackTrace();
                return -1;
            }
            n = this.getAvailableBytes-randomAccessFile.getFilePointer();

        }

        return (int) n;
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
        super.close();
    }

    @Override
    public int read() throws IOException {
        throw new IOException("fuck off");
    }

    @Override
    public int read(byte[] buffer) throws IOException {
        throw new IOException("fuck off");
    }

    @Override
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {

        int available = available();

        if(available==-1)
        {
            return -1;
        }

        int cnt = (available < byteCount) ? available : byteCount;
        return randomAccessFile.read(buffer, byteOffset, cnt);
    }

    @Override
    public long skip(long byteCount) throws IOException {

        if(byteCount<=0)
            return 0;
        long available = this.getAvailableBytes-randomAccessFile.getFilePointer();

        while(available <= 0) {

            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1;
            }
            available=this.getAvailableBytes-randomAccessFile.getFilePointer();
        }

        long skipped = (available < byteCount) ? available : byteCount;

        return randomAccessFile.skipBytes((int) skipped);

    }

    /*public interface GetAvailableBytes {
        long getAvailableBytes();
    }*/
}