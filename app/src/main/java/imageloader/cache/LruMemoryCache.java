package imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by zy on 16-5-18.
 */
public class LruMemoryCache extends MemoryCache{
    public static final int DEFAULT_SIZE = (int) Runtime.getRuntime().maxMemory() / 8;

    private int size;
    private MyLruMemoryCache<String, Bitmap> memoryCache;

    public LruMemoryCache(int size) {
        this.size = size;

        memoryCache = new MyLruMemoryCache<String, Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public LruMemoryCache() {
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
