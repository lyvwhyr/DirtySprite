package com.thef33d.dirtysprite.dirtysprite.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.thef33d.dirtysprite.dirtysprite.services.ClipboardService;

/**
 * Created by feral on 8/8/16.
 */
public class ClipboardHelper extends AppCompatActivity {
    public static final String PARAM_PINTENT = "pendingIntenet";
    public static final String PARAM_CLIPBOARD_URL = "clipboardUrl";
    public static final int PARAM_RETURN_DATA = 100;
    private static boolean serviceStarted = false;

    public ClipboardHelper() { }

    public static void startService(Context context) {
        if (!serviceStarted) {
            Intent intent = new Intent(context, ClipboardService.class);
            context.startService(intent);
            serviceStarted = true;
        }
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, ClipboardService.class);
        context.stopService(intent);
    }
}
