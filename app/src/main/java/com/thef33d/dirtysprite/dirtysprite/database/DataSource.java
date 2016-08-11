package com.thef33d.dirtysprite.dirtysprite.database;

import android.content.Context;

import com.thef33d.dirtysprite.dirtysprite.database.sources.MediaSource;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by feral on 8/8/16.
 */
public class DataSource {
    private MediaSource mediaSource;

    public DataSource(Context context) {
        mediaSource = new MediaSource(context);
    }

    public MediaSource getMediaSource() {
        return mediaSource;
    }

    private Date addMinutesToDate(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return new Date(cal.getTimeInMillis());
    }
}
