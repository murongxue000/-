package com.example.mydairy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDairyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_DAIRY = "create table Dairy ("
            + "id integer primary key autoincrement, "
            + "date text, "
            + "weather text, "
            + "place text, "
            + "text text, "
            + "pictureUri text, "
            + "musicUri text, "
            + "luyinUri text)";

    private Context mContext;

    public MyDairyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_DAIRY);
        Toast.makeText(mContext,"Creat succeed",Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion ,int newVersion ){}

}