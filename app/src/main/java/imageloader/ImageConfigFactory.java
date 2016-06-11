package imageloader;

import android.content.Context;

import imageloader.Hasher.DefaultHasher;
import imageloader.Hasher.Hasher;
import imageloader.cache.DiskCache;
import imageloader.cache.MemoryCache;
import imageloader.decoder.Decoder;
import imageloader.decoder.DefaultDecoder;
import imageloader.displayer.DefaultDisplayer;
import imageloader.displayer.Displayer;
import imageloader.loader.Loader;
import imageloader.network.DefaultHttpConnection;
import imageloader.network.HttpConnection;
import imageloader.thread.DefaultThreadPool;
import imageloader.thread.ThreadPool;

/**
 * Created by zy on 16-6-10.
 */
public class ImageConfigFactory {

    public static Decoder getDefaultDecoder() {
        return new DefaultDecoder();
    }

    public static Displayer getDefaultDisplayer() {
        return new DefaultDisplayer();
    }

    public static Loader getDefaultLoader() {
        return null;
    }

    public static HttpConnection getDefaultHttpConnection() {
        return new DefaultHttpConnection();
    }

    public static ThreadPool getDefaultThreadPool() {
        return new DefaultThreadPool();
    }

    public static MemoryCache getDefaultMemoryCache() {
        return new MemoryCache();
    }

    public static DiskCache getDefaultDiskCache(Context context) {
        return new DiskCache(context);
    }

    public static Hasher getDefaultHasher() {
        return new DefaultHasher();
    }
}
