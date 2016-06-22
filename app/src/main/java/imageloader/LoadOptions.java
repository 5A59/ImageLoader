package imageloader;

import android.graphics.Bitmap;

/**
 * Created by zy on 16-6-22.
 */
public class LoadOptions {
    public static final int NO_LOAD_RES = -1;

    private Bitmap loadBitmap;
    private int loadRes = NO_LOAD_RES;

    public Bitmap getLoadBitmap() {
        return loadBitmap;
    }

    public int getLoadRes() {
        return loadRes;
    }

    public static class Builder {
        private Bitmap loadBitmap;
        private int loadRes;

        public Builder setLoadBitmap(Bitmap  bitmap) {
            loadBitmap = bitmap;
            return this;
        }

        public Builder setLoadRes(int res) {
            loadRes = res;
            return this;
        }

        private void apply(LoadOptions options) {
            options.loadBitmap = loadBitmap;
            options.loadRes = loadRes;
        }

        public LoadOptions create() {
            LoadOptions options = new LoadOptions();
            apply(options);
            return options;
        }
    }
}
