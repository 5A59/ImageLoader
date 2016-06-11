package imageloader.decoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by zy on 16-6-11.
 */
public class DefaultDecoder implements Decoder {
    @Override
    public Bitmap decode(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
