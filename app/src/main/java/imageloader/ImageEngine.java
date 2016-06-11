package imageloader;

import android.graphics.Bitmap;

import imageloader.thread.ThreadPool;

/**
 * Created by zy on 16-5-27.
 */
public class ImageEngine {

    private ThreadPool sourceLoadPool;
    private ThreadPool cacheLoadPool;
    private ThreadPool execPool;
    private ImageConfig config;

    public ImageEngine(ImageConfig config) {
        this.config = config;
        sourceLoadPool = config.getThreadPool();
        cacheLoadPool = config.getThreadPool();
        checkAndSet();
    }

    public void submit(final LoadAndDisplayTask task) {
        execPool.submit(new Runnable() {
            @Override
            public void run() {
                boolean exist = config.getDiskCache().exists(config.getHasher().hash(task.getInfo().url));
                if (exist){
                    cacheLoadPool.submit(task);
                }else {
                    sourceLoadPool.submit(task);
                }
            }
        });
    }

    public void checkAndSet() {
        if (sourceLoadPool == null){
            sourceLoadPool = ImageConfigFactory.getDefaultThreadPool();
        }

        if (cacheLoadPool == null){
            cacheLoadPool = ImageConfigFactory.getDefaultThreadPool();
        }

        if (execPool == null){
            execPool = ImageConfigFactory.getDefaultThreadPool();
        }
    }
}
