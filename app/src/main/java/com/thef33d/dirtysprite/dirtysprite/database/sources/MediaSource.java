package com.thef33d.dirtysprite.dirtysprite.database.sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.thef33d.dirtysprite.dirtysprite.database.tables.MediaTable;
import com.thef33d.dirtysprite.dirtysprite.database.MySQLHelper;
import com.thef33d.dirtysprite.dirtysprite.models.Media;
import com.thef33d.dirtysprite.dirtysprite.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feral on 8/7/16.
 */
public class MediaSource {
    //represents top level of abstraction from dataBase
    //all work with db layer must be done in this class
    //getReadableDatabase() and dbHelper.getWritableDatabase() must not be called outside this class
    private static final String ERROR_TOAST = "db access error";
    private MySQLHelper dbHelper;
    private Context context;

    public MediaSource(Context context) {
        this.context = context.getApplicationContext();
        this.dbHelper = MySQLHelper.getInstance(this.context);
    }

    public long create(Media media) {
        long id = -1;
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = MediaTable.getContentValues(media);
            id = db.insert(MediaTable.TABLE_NAME, null, cv);
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    public boolean update(Media media) {
        int result = -1;
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = MediaTable.getContentValues(media);
            result = db.update(MediaTable.TABLE_NAME, cv, BaseColumns._ID + " = ?",
                    new String[]{String.valueOf(media.mediaId)});
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        Logger.d("result from update " + result);
        return result != -1;
    }


    public int remove(Media media) {
        int result = 0;
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            result = db.delete(MediaTable.TABLE_NAME, BaseColumns._ID + " = ? ",
                    new String[]{String.valueOf(media.mediaId)});
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Media getMediaById(long mediaId) {
        Media media = null;
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String selectQuery = "SELECT * FROM " + MediaTable.TABLE_NAME + " WHERE "
                    + BaseColumns._ID + " = '" + String.valueOf(mediaId) + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                media = new Media(cursor);
            }
            cursor.close();
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return media;
    }

    public ArrayList<Media> getAllMedia() {
        ArrayList<Media> mediaList = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM " + MediaTable.TABLE_NAME + " ORDER BY `parseDate` DESC;";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    mediaList.add(new Media(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return mediaList;
    }

    public ArrayList<Media> getParsedMedia() {
        ArrayList<Media> mediaList = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM " +
                    MediaTable.TABLE_NAME +
                    " WHERE `type` IS NOT NULL ORDER BY `_id` DESC";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    mediaList.add(new Media(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return mediaList;
    }

    public ArrayList<Media> getUnparsedMedia() {
        ArrayList<Media> mediaList = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM " +
                    MediaTable.TABLE_NAME +
                    " WHERE `type` IS NULL ORDER BY `_id` ASC";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    mediaList.add(new Media(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException e) {
            Toast.makeText(context, ERROR_TOAST, Toast.LENGTH_SHORT).show();
        }
        return mediaList;
    }

    /*
    public class GetMediaListEvent {
        public final List<Media> list;
        public GetMediaLisEvent(List<Media> list) {
            this.list = list;
        }
    }
    */
}
