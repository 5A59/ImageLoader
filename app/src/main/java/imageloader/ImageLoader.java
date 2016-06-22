package imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import imageloader.viewpack.ImageViewPack;
import imageloader.viewpack.ViewPack;
import utils.Utils;
import zy.com.imageloader.R;

/**
 * Created by zy on 16-5-18.
 */
public class ImageLoader {
    private static ImageLoader ourInstance = new ImageLoader();

    private ImageConfig config;
    private ImageEngine engine;
    private Handler handler;

    public static ImageLoader getInstance() {
        return ourInstance;
    }

    private ImageLoader() {
    }

    public void init(ImageConfig config) {
        this.config = config;
        engine = new ImageEngine(config);
        handler = new Handler();
    }

    public void displayImage(String url, ViewPack viewPack, LoadOptions options) {
        //检查 memorycache 中是否有缓存，有的话直接display
        Bitmap bitmap = config.getMemoryCache().get(Utils.hash(url));
        if (bitmap != null){
            new DisplayTask(config, new TaskInfo(url, bitmap, viewPack, null)).run();
            return ;
        }
        if (options != null){
            if (options.getLoadRes() != LoadOptions.NO_LOAD_RES){
                viewPack.setBitmap(BitmapFactory.decodeResource(config.getResources(), options.getLoadRes()));
            }else if (options.getLoadBitmap() != null) {
                viewPack.setBitmap(options.getLoadBitmap());
            }
        }

        LoadAndDisplayTask loadAndDisplayTask = new LoadAndDisplayTask(config,
                new TaskInfo(url, bitmap, viewPack, null), handler);
        engine.submit(loadAndDisplayTask);
    }

    public void displayImage(String url, ImageView view, LoadOptions options) {
        displayImage(url, new ImageViewPack(view), options);
    }

    public void displayImage(String url, ViewPack viewPack) {
        displayImage(url, viewPack, null);
    }

    public void displayImage(String url, ImageView view) {
        displayImage(url, new ImageViewPack(view));
    }

    public void loadImage() {

    }

}
