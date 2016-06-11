package imageloader.cache;

import android.graphics.Bitmap;

import cache.LruCache;

/**
 * Created by zy on 16-5-18.
 */
public class MemoryCache implements LoadCache{
    public static final int DEFAULT_SIZE = (int) Runtime.getRuntime().maxMemory() / 8;

    private int size;
    private LruCache<String, Bitmap> memoryCache;

    public MemoryCache(int size) {
        this.size = size;

        memoryCache = new LruCache<String, Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public MemoryCache() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        memoryCache.put(key, bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return memoryCache.get(key);
    }

    @Override
    public boolean exists(String key) {
        return memoryCache.get(key) != null;
    }
}
