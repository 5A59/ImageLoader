package imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Logger;

/**
 * Created by zy on 16-5-18.
 */
public class ImageLoader {
    private static final int THREAD_NUM = 5;
    private static final int HANDLE_IMG_DOWNLOADED = 0;
    private static ImageLoader ourInstance = new ImageLoader();

    private Builder builder;
    private LoadCache loadCache;
    private ExecutorService executorService;
    private HttpConnection connection;

    public static ImageLoader getInstance() {
        return ourInstance;
    }

    private ImageLoader() {
        loadCache = new MemoryCache();
        executorService = Executors.newFixedThreadPool(THREAD_NUM);
        connection = new DefaultHttpConnection();
    }

    public void init(Builder builder) {
        if (builder == null){
            return ;
        }
        this.builder = builder;
        builder.apply(this);

    }

    public void init(){
        init(null);
    }

    public void load(String url, ImageView imageView) {
        String key = Utils.keyHash(url);
        Bitmap bitmap = loadCache.get(key);
        imageView.setTag(url);

        if (bitmap == null){
            downloadImage(url, imageView);
        }

        if (url.equals(imageView.getTag())){
            imageView.setImageBitmap(bitmap);
        }
    }

    public void downloadImage(final String url, final ImageView imageView) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = connection.get(url);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                if (url.equals(imageView.getTag())){
                    imageView.setImageBitmap(bitmap);
                }
                loadCache.put(Utils.keyHash(url), bitmap);

                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static class Builder {

        private LoadCache loadCache;
        private ExecutorService executorService;
        private HttpConnection connection;

        public Builder() {

        }

        public Builder setCache(LoadCache loadCache) {
            this.loadCache = loadCache;
            return this;
        }

        public Builder setExceutor(ExecutorService exceutor) {
            this.executorService = exceutor;
            return this;
        }

        public Builder setConnection(HttpConnection connection) {
            this.connection = connection;
            return this;
        }

        public void apply(ImageLoader imageLoader){
            if (executorService != null){
                imageLoader.executorService = this.executorService;
            }
            if (loadCache != null){
                imageLoader.loadCache = this.loadCache;
            }
            if (connection != null){
                imageLoader.connection = this.connection;
            }
        }
    }

    public static class Utils {
        public static String keyHash(String key) {
            return key;
        }
    }
}
