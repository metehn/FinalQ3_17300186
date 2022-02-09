package me.metehanersoy.finalq3_17300186;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Core extends SQLiteOpenHelper {

    private static final String core_db="core";
    private static final int Version =1;


    public Core(Context c ){

        super(c,core_db,null,Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  MyTable(course TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS MyTable");
        onCreate(db);

    }
}
