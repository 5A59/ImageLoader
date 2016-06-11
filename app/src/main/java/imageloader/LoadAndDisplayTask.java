package imageloader;

import android.graphics.Bitmap;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import imageloader.viewpack.ViewPack;

/**
 * Created by zy on 16-6-11.
 */
public class LoadAndDisplayTask implements Runnable {

    private ImageConfig config;
    private TaskInfo info;

    public LoadAndDisplayTask(ImageConfig config, TaskInfo info) {
        this.config = config;
        this.info = info;
    }

    public TaskInfo getInfo() {
        return info;
    }

    @Override
    public void run() {
        Bitmap bitmap = config.getMemoryCache().get(config.getHasher().hash(info.url));
        ViewPack viewPack = info.viewPack;
        if (bitmap != null){
            DisplayTask displayTask = new DisplayTask(config, info);
            displayTask.run();
            return ;
        }

        bitmap = config.getDiskCache().get(config.getHasher().hash(info.url));
        if (bitmap != null){
            DisplayTask displayTask = new DisplayTask(config, info);
            displayTask.run();
            config.getMemoryCache().put(config.getHasher().hash(info.url), bitmap);
            return ;
        }

        InputStream inputStream = config.getConnection().get(info.url);
        bitmap = config.getDecoder().decode(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.bitmap = bitmap;
        config.getDiskCache().put(config.getHasher().hash(info.url), bitmap);
        DisplayTask displayTask = new DisplayTask(config, info);
        displayTask.run();
    }
}
