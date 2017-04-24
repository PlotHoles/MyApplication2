package com.sparecode.yaaroz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sparecode.yaaroz.utils.DebugLog;

/**
 * Created by Sanket on 4/4/2017.
 */

public class YaarozDatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "yaaroz";

    // Contacts table name
    private static final String TABLE_USER = "user";

    //USER TABLE FILED NAME
    private static final String USERID = "user_id";
    private static final String CITYID = "city_id";

    public YaarozDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
    }

    private void createUserTable(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + "ID" + " INTEGER PRIMARY KEY," + USERID + " TEXT,"
                + CITYID + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    public long addUserWithCitySelection(String userId, String cityId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERID, userId);
        contentValues.put(CITYID, cityId);
        return sqLiteDatabase.insert(TABLE_USER, null, contentValues);
    }

    public boolean isUserHasSelectedCity(String userId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USER, new String[]{CITYID}, USERID + " =?", new String[]{userId}, null, null, null, null);
        DebugLog.e("CURSOR SIZE:::" + cursor.getCount());
        return cursor.getCount() > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

}
