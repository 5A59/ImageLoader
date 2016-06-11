package imageloader;

import android.graphics.Bitmap;

import imageloader.viewpack.ViewPack;

/**
 * Created by zy on 16-6-11.
 */
public class TaskInfo {
    public Object tag;
    public String url;
    public Bitmap bitmap;
    public ViewPack viewPack;

    public TaskInfo(String url, Bitmap bitmap, ViewPack viewPack, Object tag) {
        this.url = url;
        this.bitmap = bitmap;
        this.viewPack = viewPack;
        this.tag = tag;
    }

    public TaskInfo() {

    }
}
