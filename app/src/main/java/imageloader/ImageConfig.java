package imageloader;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;

import imageloader.Hasher.Hasher;
import imageloader.cache.DiskCache;
import imageloader.cache.MemoryCache;
import imageloader.decoder.Decoder;
import imageloader.displayer.Displayer;
import imageloader.loader.Loader;
import imageloader.network.HttpConnection;
import imageloader.thread.ThreadPool;

/**
 * Created by zy on 16-6-10.
 */
public class ImageConfig {
    private Resources resources;
    private Decoder decoder;
    private Displayer displayer;
    private Loader loader;
    private HttpConnection connection;
    private ThreadPool threadPool;
    private MemoryCache memoryCache;
    private DiskCache diskCache;
    private Hasher hasher;

    public Resources getResources() {
        return resources;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public DiskCache getDiskCache() {
        return diskCache;
    }

    public MemoryCache getMemoryCache() {
        return memoryCache;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public HttpConnection getConnection() {
        return connection;
    }

    public Loader getLoader() {
        return loader;
    }

    public Displayer getDisplayer() {
        return displayer;
    }

    public Hasher getHasher() {
        return hasher;
    }

    public static class Builder {
        private Decoder decoder;
        private Displayer displayer;
        private Loader loader;
        private HttpConnection connection;
        private ThreadPool threadPool;
        private MemoryCache memoryCache;
        private DiskCache diskCache;
        private Hasher hasher;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }
        
        public Builder setDecoder(Decoder decoder) {
            this.decoder = decoder;
            return this;
        }

        public Builder setDisplayer(Displayer displayer) {
            this.displayer = displayer;
            return this;
        }

        public Builder setLoader(Loader loader) {
            this.loader = loader;
            return this;
        }

        public Builder setHttpConnection(HttpConnection connection) {
            this.connection = connection;
            return this;
        }

        public Builder setThreadPool(ThreadPool threadPool) {
            this.threadPool = threadPool;
            return this;
        }

        public Builder setMemoryCache(MemoryCache memoryCache) {
            this.memoryCache = memoryCache;
            return this;
        }

        public Builder setDiskCache(DiskCache diskCache) {
            this.diskCache = diskCache;
            return this;
        }

        public Builder setHasher(Hasher hasher) {
            this.hasher = hasher;
            return this;
        }

        public ImageConfig create() {
            ImageConfig config = new ImageConfig();
            apply(config);
            return config;
        }

        private void apply(ImageConfig config) {
            config.resources = context.getResources();

            if (decoder != null){
                config.decoder = decoder;
            }else {
                config.decoder = ImageConfigFactory.getDefaultDecoder();
            }

            if (displayer != null){
                config.displayer = displayer;
            }else {
                config.displayer = ImageConfigFactory.getDefaultDisplayer();
            }

            if (loader != null){
                config.loader = loader;
            }else {
                config.loader = ImageConfigFactory.getDefaultLoader();
            }

            if (connection != null){
                config.connection = connection;
            }else {
                config.connection = ImageConfigFactory.getDefaultHttpConnection();
            }

            if (threadPool != null){
                config.threadPool = threadPool;
            }else {
                config.threadPool = ImageConfigFactory.getDefaultThreadPool();
            }

            if (memoryCache != null){
                config.memoryCache = memoryCache;
            }else {
                config.memoryCache = ImageConfigFactory.getDefaultMemoryCache();
            }

            if (diskCache != null){
                config.diskCache = diskCache;
            }else {
                config.diskCache = ImageConfigFactory.getDefaultDiskCache(context);
            }

            if (hasher != null){
                config.hasher = hasher;
            }else {
                config.hasher = ImageConfigFactory.getDefaultHasher();
            }
        }
    }
}
