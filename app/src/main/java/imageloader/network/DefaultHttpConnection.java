package imageloader.network;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zy on 16-5-19.
 */
public class DefaultHttpConnection implements HttpConnection{

    private HttpURLConnection connection;

    public DefaultHttpConnection() {

    }

    @Override
    public InputStream get(String picUrl) {
        try {
            URL url = new URL(picUrl);
            connection = (HttpURLConnection) url.openConnection();
            return connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {
        if (connection != null){
            connection.disconnect();
        }
    }
}
