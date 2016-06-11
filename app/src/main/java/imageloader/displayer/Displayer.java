package imageloader.displayer;

import android.graphics.Bitmap;

import imageloader.viewpack.ViewPack;

/**
 * Created by zy on 16-5-26.
 *
 * 用于在 viewpack 上显示图片
 */
public interface Displayer {
    void display(Bitmap bitmap, ViewPack viewPack);
}
