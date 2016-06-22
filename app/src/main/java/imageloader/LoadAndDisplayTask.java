package imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import imageloader.viewpack.ViewPack;
import utils.Logger;
import zy.com.imageloader.R;

/**
 * Created by zy on 16-6-11.
 */
public class LoadAndDisplayTask implements Runnable {

    private ImageConfig config;
    private TaskInfo info;
    private Handler handler;

    public LoadAndDisplayTask(ImageConfig config, TaskInfo info, Handler handler) {
        this.config = config;
        this.info = info;
        this.handler = handler;
    }

    public TaskInfo getInfo() {
        return info;
    }

    @Override
    public void run() {
        Bitmap bitmap = config.getMemoryCache().get(config.getHasher().hash(info.url));
        if (bitmap != null){
            DisplayTask displayTask = new DisplayTask(config, info);
            displayTask.run();
            return ;
        }

        setTag(info);

        bitmap = config.getDiskCache().get(config.getHasher().hash(info.url));
        if (bitmap != null){
            info.bitmap = bitmap;
            DisplayTask displayTask = new DisplayTask(config, info);
            handler.post(displayTask);
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
        config.getMemoryCache().put(config.getHasher().hash(info.url), bitmap);

        DisplayTask displayTask = new DisplayTask(config, info);
        handler.post(displayTask);
    }

    private void setTag(TaskInfo info) {
        View v = info.viewPack.getView();
        v.setTag(info.url);
        info.tag = info.url;
    }
}
