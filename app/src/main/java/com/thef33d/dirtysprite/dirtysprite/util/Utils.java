package com.thef33d.dirtysprite.dirtysprite.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.thef33d.dirtysprite.dirtysprite.R;
import com.thef33d.dirtysprite.dirtysprite.activities.ClipboardHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by feral on 8/5/16.
 */

public class Utils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    public static boolean isValidUrl(String url) {
        String URL_RE = "((https?)://)?[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        return Pattern.matches(URL_RE, url);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static double roundResult(double value, int decimalSigns) {
        if (decimalSigns < 0 || decimalSigns > 5) {
            Logger.d("decimalSigns meant to be bw 0-5. Request is: " + String.valueOf(decimalSigns));
            if (decimalSigns < 0) decimalSigns = 0;
            if (decimalSigns > 5) decimalSigns = 5;
        }
        double multiplier = Math.pow(10.0, (double) decimalSigns);//всегда .0
        long numerator  = Math.round(value * multiplier);
        return numerator / multiplier;
    }

    public static int generateInt(int from, int to) {
        to -= from;
        return from + (int) (Math.random() * ++to);
    }

    public static String getStringDateFromCal(Calendar date, Context context){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTimeInMillis());
        Locale locale = context.getResources().getConfiguration().locale;
        return String.format(locale, "%02d.%02d.%02d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR) % 100);
    }

    public static String getStringTimeFromCal(Calendar date, Context context){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTimeInMillis());
        Locale locale = context.getResources().getConfiguration().locale;
        return String.format(locale, "%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }

    public static void logDate(String dateName, Calendar dateToLog, Context context){
        Locale locale = context.getResources().getConfiguration().locale;
        String log = String.format(locale, "%02d.%02d.%04d %02d:%02d:%02d", dateToLog.get(Calendar.DAY_OF_MONTH),
                dateToLog.get(Calendar.MONTH) + 1, dateToLog.get(Calendar.YEAR), dateToLog.get(Calendar.HOUR_OF_DAY),
                dateToLog.get(Calendar.MINUTE), dateToLog.get(Calendar.SECOND));
        if (dateName.length() >= 20) {
            Logger.d(dateName + log);
        } else {
            while (dateName.length() < 20) dateName += '.';
            Logger.d(dateName + log);
        }
    }

    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void openQuitDialog(final AppCompatActivity currentActivity) {
        //note that you shouldn't pass getApplicationContext() to AlertDialog.Builder. it waits for Activity
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(currentActivity);
        quitDialog.setTitle(currentActivity.getResources().getString(R.string.exitConfirmation));
        quitDialog.setNegativeButton(currentActivity.getResources().getString(R.string.no), new DialogInterface.OnClickListener() { @Override
        public void onClick(DialogInterface dialog, int which) { /*NOP*/ }
        });
        quitDialog.setPositiveButton(currentActivity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipboardHelper.stopService(currentActivity.getApplicationContext());
                currentActivity.finish();
            }
        });
        quitDialog.show();
    }

}