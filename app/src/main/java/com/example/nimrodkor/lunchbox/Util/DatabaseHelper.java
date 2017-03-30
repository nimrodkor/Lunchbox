package com.example.nimrodkor.lunchbox.Util;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.text.DateFormat;

import rx.Observable;
import rx.schedulers.Schedulers;

import static com.example.nimrodkor.lunchbox.Util.DatabaseHandler.KEY_DATE;
import static com.example.nimrodkor.lunchbox.Util.DatabaseHandler.KEY_USER_ID;
import static com.example.nimrodkor.lunchbox.Util.DatabaseHandler.TABLE_LUNCHBOXES;

public class DatabaseHelper {
    public static SqlBrite sqlBrite = new SqlBrite.Builder().build();
    DatabaseHandler dbHandler;
    BriteDatabase db = sqlBrite.wrapDatabaseHelper(dbHandler, Schedulers.io());

    public DatabaseHelper (Activity activity) {
        dbHandler = new DatabaseHandler(activity);
    }

    public Lunchbox getCurrentUserLunchbox(String id) {
        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).toString();
        Log.v("Current Date: ", date);
        Observable<SqlBrite.Query> lunchbox = db.createQuery(TABLE_LUNCHBOXES,"SELECT * FROM " + TABLE_LUNCHBOXES
                + " WHERE " + KEY_USER_ID + " = " + id + " AND " + KEY_DATE + " = " + date);
        lunchbox.subscribe((query -> {
            Cursor cursor = query.run();

        }));
        return null;
    }
}
