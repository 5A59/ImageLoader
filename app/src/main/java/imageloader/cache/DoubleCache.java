package imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by zy on 16-5-19.
 */
public class DoubleCache implements LoadCache{

    private LruMemoryCache lruMemoryCache;
    private LruDiskCache lruDiskCache;

    private Context context;

    public DoubleCache(Context context) {
        this.context = context;
        lruMemoryCache = new LruMemoryCache();
        lruDiskCache = new LruDiskCache(context);
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        putToCache(key, bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return getFromCache(key);
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    public void putToCache(String key, Bitmap bitmap) {
        lruMemoryCache.put(key, bitmap);
        lruDiskCache.put(key, bitmap);
    }

    public Bitmap getFromCache(String key) {
        Bitmap bitmap = null;
        if ((bitmap = lruMemoryCache.get(key)) == null){
            bitmap = lruDiskCache.get(key);
        }
        return bitmap;
    }
}
