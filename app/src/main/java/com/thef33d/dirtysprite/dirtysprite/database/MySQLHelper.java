package com.thef33d.dirtysprite.dirtysprite.database;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.thef33d.dirtysprite.dirtysprite.database.tables.MediaTable;
import com.thef33d.dirtysprite.dirtysprite.util.Logger;

/**
 * Created by feral on 8/6/16.
 */

public class MySQLHelper extends SQLiteOpenHelper {
    //helper is one no matter how much tables there are
    private static final String DB_NAME = "dirtySprite.db";
    private static final int DB_VERSION = 1;
    private static MySQLHelper instance;
    public static final String CREATE_TABLE = "CREATE TABLE %s ( %s);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS %s";
    public static final String PRIMARY_KEY = BaseColumns._ID + " integer primary key autoincrement, ";
    private final Resources res;

    //singleton protects db from multi thread concurrent access
    private MySQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        res = context.getResources();
    }

    public static MySQLHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MySQLHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db);
        Logger.d("MySQLHelper: onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.d("MySQLHelper: Found new DB version. About to update to: " + String.valueOf(DB_VERSION));
        updateMyDatabase(db);
    }

    private void updateMyDatabase(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(getCreateSql(MediaTable.TABLE_NAME,
                    MediaTable.FIELDS));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d("MySQLHelper: failed to create media table");
        } finally {
            db.endTransaction();
        }

    }

    private String getCreateSql(String tableName, String fields) {
        return String.format(CREATE_TABLE, tableName, fields);
    }

    //is accessible from each of the Sources because each of them hold link to singleton dbHelper
    public void dropAllTablesInDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL(getDropSql(MediaTable.TABLE_NAME));
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        updateMyDatabase(db);
    }
    private String getDropSql(String tableName) {
        return String.format(DROP_TABLE, tableName);
    }
}