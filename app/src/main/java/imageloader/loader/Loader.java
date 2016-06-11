package imageloader.loader;

import java.io.InputStream;

/**
 * Created by zy on 16-5-26.
 *
 * 加载器接口，用于从网络或者文件或者其他地方获取文件流
 */
public interface Loader {
    InputStream load(String uri);
}
