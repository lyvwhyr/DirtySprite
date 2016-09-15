package com.thef33d.dirtysprite.dirtysprite.util;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.io.File;

import static android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED;
/**
 * Created by feral on 9/14/16.
 */
public class Downloader {


    public static long start(Context context, String url, File saveFile) {
        return start(context, url, saveFile,null,null);
    }

    public static long start(Context context, String url, File saveFile, CharSequence title, CharSequence description) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationUri(Uri.fromFile(saveFile))
                .allowScanningByMediaScanner();
        if (TextUtils.isEmpty(title)) {
            request.setTitle(title);
        }
        if (TextUtils.isEmpty(description)) {
            request.setDescription(description);
        }
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        return manager.enqueue(request);
    }

}