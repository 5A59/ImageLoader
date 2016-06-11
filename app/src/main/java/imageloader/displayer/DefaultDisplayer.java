package imageloader.displayer;

import android.graphics.Bitmap;

import imageloader.viewpack.ViewPack;

/**
 * Created by zy on 16-6-11.
 */
public class DefaultDisplayer implements Displayer {

    @Override
    public void display(Bitmap bitmap, ViewPack viewPack) {
        viewPack.setBitmap(bitmap);
    }
}
