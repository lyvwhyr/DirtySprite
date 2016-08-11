package com.thef33d.dirtysprite.dirtysprite.models;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.thef33d.dirtysprite.dirtysprite.database.tables.MediaTable;
import com.thef33d.dirtysprite.dirtysprite.util.Utils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by feral on 8/7/16.
 */
public class Media {

    public long mediaId;
    public String type;
    public Date parseDate;

    public String previewImg;
    public String image;
    public String video;
    public String url;
    public Date postDate;


    public String creatorName;
    public String creatorAvi;
    public String creatorUrl;

    public Integer isSavedToDevice;

    public Media() { }

    public Media(String type, long mediaId, String url, Date parseDate,
          String previewImg, String image, String video, Date postDate,
          String creatorName, String creatorAvi, String creatorUrl,
          Integer isSavedToDevice) {

        this.mediaId = mediaId;
        this.type = type;
        this.url = url;
        this.parseDate = parseDate;

        this.previewImg = previewImg;
        this.image = image;
        this.video = video;
        this.postDate = postDate;


        this.creatorName = creatorName;
        this.creatorAvi = creatorAvi;
        this.creatorUrl = creatorUrl;

        this.isSavedToDevice = isSavedToDevice;
    }

    public Media(String url) {
        this.mediaId = -1;
        this.url = url;
        this.isSavedToDevice = 0;
    }

    private Date getParseDateFromCursor(Cursor cursor) {
        try {
            return Utils.dateFormat.parse(cursor.getString(cursor.getColumnIndex(MediaTable.POST_DATE)));
        } catch (Exception e) {
            return null;
        }
    }

    private Date getPostDateFromCursor(Cursor cursor) {
        try {
             return Utils.dateFormat.parse(cursor.getString(cursor.getColumnIndex(MediaTable.POST_DATE)));
        } catch (Exception e) {
            return null;
        }
    }


    public Media(Cursor cursor) {
        this.mediaId = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));

        this.parseDate = getParseDateFromCursor(cursor);
        this.postDate = getPostDateFromCursor(cursor);
        this.type = cursor.getString(cursor.getColumnIndex(MediaTable.TYPE));
        this.url = cursor.getString(cursor.getColumnIndex(MediaTable.URL));

        this.previewImg = cursor.getString(cursor.getColumnIndex(MediaTable.PREVIEW_IMAGE));
        this.image = cursor.getString(cursor.getColumnIndex(MediaTable.IMAGE));
        this.video = cursor.getString(cursor.getColumnIndex(MediaTable.VIDEO));

        this.creatorName = cursor.getString(cursor.getColumnIndex(MediaTable.CREATOR_NAME));
        this.creatorAvi = cursor.getString(cursor.getColumnIndex(MediaTable.CREATOR_AVI));
        this.creatorUrl = cursor.getString(cursor.getColumnIndex(MediaTable.CREATOR_URL));

        this.isSavedToDevice = cursor.getInt(cursor.getColumnIndex(MediaTable.IS_SAVED_TO_DEVICE));
    }
}
