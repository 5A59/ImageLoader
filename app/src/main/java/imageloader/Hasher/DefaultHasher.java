package imageloader.Hasher;

/**
 * Created by zy on 16-6-11.
 */
public class DefaultHasher implements Hasher {

    @Override
    public String hash(String url) {
        return url;
    }
}
