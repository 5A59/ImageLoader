package imageloader.viewpack;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import utils.Logger;

/**
 * Created by zy on 16-6-11.
 */
public class ImageViewPack implements ViewPack{

    private ImageView imageView;

    public ImageViewPack(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public View getView() {
        return imageView;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void setDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
}
