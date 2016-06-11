package imageloader;

import android.graphics.Bitmap;

import imageloader.viewpack.ViewPack;
import utils.Logger;

/**
 * Created by zy on 16-6-11.
 */
public class DisplayTask implements Runnable{

    private ImageConfig config;
    private TaskInfo info;

    public DisplayTask(ImageConfig config, TaskInfo info) {
        this.config = config;
        this.info = info;
    }

    @Override
    public void run() {
//        if (info.tag == null || !((String)info.tag).equals(info.viewPack.getView().getTag())){
//            return ;
//        }

        config.getDisplayer().display(info.bitmap, info.viewPack);
    }
}
