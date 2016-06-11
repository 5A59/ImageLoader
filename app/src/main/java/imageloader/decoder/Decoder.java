package imageloader.decoder;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by zy on 16-5-26.
 */
public interface Decoder {
    Bitmap decode(InputStream inputStream);
}
