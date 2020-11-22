package com.example.mynotebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "word text, "
            + "meaning text, "
            + "sentence text,"
            + "yORn text)"; //判断这个词是否认识

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Creat succeed",Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion ,int newVersion ){}

}
