package com.example.nimrodkor.lunchbox.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    static final String DATABASE_NAME = "epiz_19899092_lunchbox";

    // table names
    static final String TABLE_USERS = "Users";
    static final String TABLE_LUNCHBOXES = "Lunchboxes";
    static final String TABLE_FOUND_MATCH = "FoundMatch";
    static final String TABLE_MATCHES = "Matches";

    // Users Table Columns names
    static final String KEY_USER_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_RATING = "rating";

    // LUNCHBOXES Table Columns name
    static final String KEY_LB_ID = "lunchbox_id";
    static final String KEY_LB_USER_ID = "user_id";
    static final String KEY_IMAGE_URL = "image_url";
    static final String KEY_TIME_WINDOW = "time_window";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_DATE = "date";
    static final String KEY_FOUND_MATCH = "found_match";

    // FoundMatches Table Columns names
    static final String KEY_MATCH_ID = "match";
    static final String KEY_LB_1 = "Lunchbox_1";
    static final String KEY_LB_2 = "Lunchbox_2";
    static final String KEY_USER_ID_1 = "user_id";
    static final String KEY_USER_ID_2 = "user_id";

    // Matches Table Columns names
    static final String KEY_LB_ID_LIKED = "lb_id_liked";
    static final String KEY_USER_LIKED_ID = "user_liked_id";


    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_RATING + " TEXT" + ")";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_LUNCHBOXES + "("
                + KEY_LB_ID + " INTEGER PRIMARY KEY," + KEY_LB_USER_ID + " REFERENCES " + TABLE_USERS + "." + KEY_USER_ID + ","
                + KEY_IMAGE_URL + " TEXT," + KEY_TIME_WINDOW + " TEXT," + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_FOUND_MATCH + "TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_FOUND_MATCH + "("
                + KEY_MATCH_ID + " INTEGER PRIMARY KEY," + KEY_LB_1 + " REFERENCES " + TABLE_LUNCHBOXES + "." + KEY_LB_ID + ","
                + KEY_LB_2 + " REFERENCES " + TABLE_LUNCHBOXES + "." + KEY_LB_ID + ","
                + KEY_USER_ID_1 + " REFERENCES " + TABLE_USERS + "." + KEY_USER_ID + ","
                + KEY_USER_ID_2 + " REFERENCES " + TABLE_USERS + "." + KEY_USER_ID + ")";
        db.execSQL(query);

        query = "CREATE TABLE" + TABLE_MATCHES + "("
                + KEY_LB_ID_LIKED + " REFERENCES " + TABLE_LUNCHBOXES + "." + KEY_LB_ID + ","
                + KEY_USER_LIKED_ID + " REFERENCES " + TABLE_USERS + "." + KEY_USER_ID + ")";
        db.execSQL(query);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LUNCHBOXES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOUND_MATCH);

        // Create tables again
        onCreate(db);
    }
}
