package imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import imageloader.viewpack.ImageViewPack;
import imageloader.viewpack.ViewPack;
import utils.Utils;

/**
 * Created by zy on 16-5-18.
 */
public class ImageLoader {
    private static ImageLoader ourInstance = new ImageLoader();

    private ImageConfig config;
    private ImageEngine engine;

    public static ImageLoader getInstance() {
        return ourInstance;
    }

    private ImageLoader() {
    }

    public void init(ImageConfig config) {
        this.config = config;
        engine = new ImageEngine(config);
    }

    public void displayImage(String url, ViewPack viewPack) {
        //检查 memorycache 中是否有缓存，有的话直接display
        Bitmap bitmap = config.getMemoryCache().get(Utils.hash(url));
        if (bitmap != null){
            new DisplayTask(config, new TaskInfo(url, bitmap, viewPack, null)).run();
            return ;
        }
        LoadAndDisplayTask loadAndDisplayTask = new LoadAndDisplayTask(config, new TaskInfo(url, bitmap, viewPack, null));
        engine.submit(loadAndDisplayTask);
    }

    public void displayImage(String url, ImageView view) {
        displayImage(url, new ImageViewPack(view));
    }

    public void loadImage() {

    }

}
