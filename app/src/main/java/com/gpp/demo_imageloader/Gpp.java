package com.gpp.demo_imageloader;

import android.util.Log;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Gpp {
    public static final String TAG = "gpp";
    public static final String TAG1 = "xxx";

    private static boolean isShow=true;

    public static void e(String msg) {
        if (isShow) {
            Log.e(TAG, msg);
        }
    }
    public static void w(String msg) {
        if (isShow) {
            Log.w(TAG1, msg);
        }
    }
}
