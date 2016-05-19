package utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zy on 16-5-19.
 */
public class Utils {

    public static int appVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String md5(String data) {
        String key;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data.getBytes());
            byte[] bytes = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes){
                int bt = b & 0xFF;
                if (bt < 16){
                    stringBuilder.append('0');
                }
                stringBuilder.append(Integer.toHexString(bt));
            }

            key = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            key = "";
        }
        return key;
    }

    public static File getCacheDir(Context context, String subName) {
        String path = context.getCacheDir().getPath();
        return new File(path + File.separator + subName);
    }
}
