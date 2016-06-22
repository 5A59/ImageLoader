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
        String tag = objToString(info.tag);
        String vTag = objToString(info.viewPack.getView().getTag());
        if (tag != null && !tag.equals(vTag)){
            return ;
        }

        if (info.bitmap != null){
            config.getDisplayer().display(info.bitmap, info.viewPack);
        }
    }

    private String objToString(Object object) {
        if (object instanceof String){
            return (String) object;
        }
        return null;
    }
}
