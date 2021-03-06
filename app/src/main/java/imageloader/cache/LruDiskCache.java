package imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.IOException;
import java.io.OutputStream;

import utils.Utils;

/**
 * Created by zy on 16-5-19.
 */
public class LruDiskCache extends DiskCache {
    private static final String SUBNAME = "img";
    private static final int VALUE_COUNT = 1;
    private static final int INDEX = 0;
    private static final int CACHE_SIZE = 20 * 1024 * 1024;

    private DiskLruCache lruCache;

    public LruDiskCache(Context context) {
        try {
            lruCache = DiskLruCache.open(Utils.getCacheDir(context, SUBNAME), Utils.appVersion(context),
                    VALUE_COUNT, CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        putBitmapToFile(getHash(key), bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return getBitmapFromFile(getHash(key));
    }

    @Override
    public boolean exists(String key) {
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = lruCache.get(Utils.md5(key));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return snapshot != null;
    }

    public String getHash(String key) {
        return Utils.md5(key);
    }

    private void putBitmapToFile(String key, Bitmap bitmap) {
        try {
            DiskLruCache.Editor editor = null;
            editor = lruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(INDEX);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            lruCache.flush();

            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromFile(String key) {
        Bitmap bitmap = null;
        try {
            DiskLruCache.Snapshot snapshot = lruCache.get(key);
            if (snapshot != null){
                bitmap = BitmapFactory.decodeStream(snapshot.getInputStream(INDEX));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}

