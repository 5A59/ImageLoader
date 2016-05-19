package imageloader;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by zy on 16-5-19.
 */
public class DoubleCache implements LoadCache{

    private MemoryCache memoryCache;
    private DiskCache diskCache;

    private Context context;

    public DoubleCache(Context context) {
        this.context = context;
        memoryCache = new MemoryCache();
        diskCache = new DiskCache(context);
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        putToCache(key, bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return getFromCache(key);
    }

    public void putToCache(String key, Bitmap bitmap) {
        memoryCache.put(key, bitmap);
        diskCache.put(key, bitmap);
    }

    public Bitmap getFromCache(String key) {
        Bitmap bitmap = null;
        if ((bitmap = memoryCache.get(key)) == null){
            bitmap = diskCache.get(key);
        }
        return bitmap;
    }
}
