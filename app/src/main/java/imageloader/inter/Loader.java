package imageloader.inter;

import java.io.InputStream;

/**
 * Created by zy on 16-5-26.
 */
public interface Loader {
    InputStream load(String uri);
}
