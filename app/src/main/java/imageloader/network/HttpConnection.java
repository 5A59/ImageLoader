package imageloader.network;

import java.io.InputStream;

/**
 * Created by zy on 16-5-18.
 */
public interface HttpConnection {
    InputStream get(String url);
    void close();
}
