package imageloader.viewpack;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by zy on 16-5-26.
 *
 * 在View基础上再封装一层
 */
public interface ViewPack {
    View getView();
    void setBitmap(Bitmap bitmap);
    void setDrawable(Drawable drawable);
}
