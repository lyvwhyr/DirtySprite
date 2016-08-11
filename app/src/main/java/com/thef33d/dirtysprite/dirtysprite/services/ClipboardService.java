package com.thef33d.dirtysprite.dirtysprite.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Intent;

import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.thef33d.dirtysprite.dirtysprite.activities.ClipboardHelper;
import com.thef33d.dirtysprite.dirtysprite.database.DataSource;
import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.Logger;
import com.thef33d.dirtysprite.dirtysprite.util.Utils;

/**
 * Created by feral on 8/5/16.
 */


/**
 * Monitors the {@link ClipboardManager} for changes and inserts into db
 */
public class ClipboardService extends Service {
    private static final String TAG = "ClipboardManager";

    static PendingIntent pi;
    Thread clipboardTracking;
    private String lastClipboardContents;
    private DataSource dataSource;

    public ClipboardService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataSource = new DataSource(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clipboardTracking = new Thread(new ServiceThread());
        clipboardTracking.run();
        return super.onStartCommand(intent, flags, startId);
    }

    public void restart() {
        lastClipboardContents = null;
    }

    private class ServiceThread implements Runnable {
        private ClipboardManager clipboardManager;
        private PendingIntent pi;

        public ServiceThread() {
        }

        @Override
        protected void finalize() throws Throwable {
            Logger.d("on finalize Service thread");
            clipboardManager.removePrimaryClipChangedListener(onPrimaryClipChangedListener);
            super.finalize();
        }

        private void sendLink() {

            if (lastClipboardContents == null){
                Logger.d("lastClipboardContents = null");
                return;
            }

            if (pi != null) {
                Logger.d("create intent 4 sending");
                Intent intent = new Intent();
                intent.putExtra(ClipboardHelper.PARAM_CLIPBOARD_URL, lastClipboardContents);
                Logger.d("sending intent");
                try {
                    pi.send(ClipboardService.this, ClipboardHelper.PARAM_RETURN_DATA, intent);
                } catch (PendingIntent.CanceledException e) {
                    Logger.d(e.getMessage());
                }
            } else{
                Logger.d("pi == null");
            }
            try {
                dataSource.getMediaSource().create(new Media(lastClipboardContents));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.d("Error by media, database not ready");
                stopSelf();
            }
        }
        @Override
        public void run() {
            Toast.makeText(ClipboardService.this, "Clipboard Servive: run", Toast.LENGTH_SHORT).show();
            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboardManager.addPrimaryClipChangedListener(onPrimaryClipChangedListener);
        }
        private OnPrimaryClipChangedListener onPrimaryClipChangedListener = new OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                Logger.d("Clipboard contents changed");
                ClipData clip = clipboardManager.getPrimaryClip();
                // if character count lower than 400 then process
                //   images and other raw data can be copied to clipboard lets not
                //     process unnecessary data
                if(clip.getItemAt(0).getText().length() < 400) {
                    lastClipboardContents = clip.getItemAt(0).getText().toString();
                    Logger.d(lastClipboardContents);
                    if(Utils.isValidUrl(lastClipboardContents)) {
                        if(lastClipboardContents.contains("instagram.com/p/")) {
                            Logger.d("instagram link detected");
                            sendLink();
                        }
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        Logger.d("on destroy");
        stopSelf();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setPendingIntent(PendingIntent pendingIntent){
        pi = pendingIntent;
        Logger.d("set pi to "+pi);
    }

    @Override
    public void onRebind(Intent intent) {
        Logger.d("on Re-bind");
        super.onRebind(intent);
    }

    public class MyBinder extends Binder {
        public ClipboardService getService() {
            return ClipboardService.this;
        }
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Logger.d("on Un-bind");
        return super.onUnbind(intent);
    }


}