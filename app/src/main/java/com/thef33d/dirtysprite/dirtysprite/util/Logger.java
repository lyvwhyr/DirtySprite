package com.thef33d.dirtysprite.dirtysprite.util;

import android.util.Log;

/**
 * Created by feral on 8/6/16.
 */
public class Logger {
    public static final String TAG = "DS_DEBUG";
    private static boolean isDebug = true;

    public static void d(String message) {
        if (isDebug) {
            Log.d(TAG, message);
        }
    }
}