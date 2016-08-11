package com.thef33d.dirtysprite.dirtysprite.database.tables;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.thef33d.dirtysprite.dirtysprite.database.MySQLHelper;
import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.Utils;

/**
 * Created by feral on 8/7/16.
 */
public class MediaTable {
    public static final String TABLE_NAME = "Media";

    public static final String MEDIA_ID = "mediaId";
    public static final String TYPE = "type";

    public static final String PARSE_DATE = "parseDate";
    public static final String URL = "url";

    public static final String PREVIEW_IMAGE = "previewImage";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String POST_DATE = "postDate";

    public static final String CREATOR_NAME = "creatorName";
    public static final String CREATOR_AVI = "creatorAvi";
    public static final String CREATOR_URL = "creatorUrl";

    public static final String IS_SAVED_TO_DEVICE = "isSavedToDevice";

    public MediaTable() { } //table cannot be instantiated

    public static final String FIELDS = MySQLHelper.PRIMARY_KEY
            + MEDIA_ID                  + " TEXT, "
            + POST_DATE                 + " TEXT, "
            + PARSE_DATE                + " TEXT, "
            + URL                       + " TEXT UNIQUE ON CONFLICT IGNORE, "
            + IMAGE                     + " TEXT UNIQUE ON CONFLICT IGNORE, "
            + VIDEO                     + " TEXT, "
            + PREVIEW_IMAGE             + " TEXT, "
            + TYPE                      + " TEXT, "
            + CREATOR_NAME              + " TEXT,"
            + CREATOR_AVI               + " TEXT,"
            + CREATOR_URL               + " TEXT, "
            + IS_SAVED_TO_DEVICE        + " INTEGER";

    public static final String CONSTRAINTS = ", UNIQUE(ID)";

    public static ContentValues getContentValues(Media media) {
        ContentValues cv = new ContentValues();
        if (media.mediaId != -1) {
            cv.put(BaseColumns._ID, media.mediaId);
        }
        cv.put(URL, media.url);

        if (media.type != null) {
            cv.put(POST_DATE, Utils.dateFormat.format(media.postDate));
            cv.put(PARSE_DATE, Utils.dateFormat.format(media.parseDate));

            cv.put(TYPE, media.type);

            cv.put(PREVIEW_IMAGE, media.previewImg);
            cv.put(IMAGE, media.image);
            cv.put(VIDEO, media.video);

            cv.put(CREATOR_NAME, media.creatorName);
            cv.put(CREATOR_AVI, media.creatorAvi);
            cv.put(CREATOR_URL, media.creatorUrl);

            cv.put(IS_SAVED_TO_DEVICE, media.isSavedToDevice);
        }
        return cv;
    }
}