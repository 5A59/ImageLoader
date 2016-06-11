package imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by zy on 16-5-18.
 */
public interface LoadCache {
    void put(String key, Bitmap bitmap);
    Bitmap get(String key);
    boolean exists(String key);
}
