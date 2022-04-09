package com.example.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;

public class DataBaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase DB;

    public DataBaseHelper(Context context) {
        super(context, "newsApp.db", null, 1);
        DB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "login text," +
                "password text," +
                "role text)");
        db.execSQL("create table news(" +
                "id integer primary key autoincrement," +
                "header text," +
                "content text," +
                "post_datetime text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists news");
    }

    public Boolean insertUser(String name, String login, String password, String role) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = DB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean insertNews(String header, String content, String post_datetime ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("header", header);
        contentValues.put("content", content);
        contentValues.put("post_datetime", post_datetime);
        long result = DB.insert("news", null, contentValues);
        return result != -1;
    }

    public Boolean deleteNews(Integer id)
    {
        long result = DB.delete("news", "id=" + id, null);
        return result != -1;
    }

    public Cursor getAllNews() {
        return DB.rawQuery("select * from news", null);
    }

    public Cursor getUserByLoginAndPassword(String login, String password) {
        return DB.rawQuery("select * from users where login = ? and password = ?", new String[]{login, password});
    }

    public Cursor getUserById(String id) {
        return DB.rawQuery("select * from users where id = ?", new String[]{id});
    }

    public Cursor getUserByLogin(String login) {
        return DB.rawQuery("select * from users where login = ?", new String[]{login});
    }

    public Cursor getNewsById(Integer id) {
        return DB.rawQuery("select * from news where id = ?", new String[]{id.toString()});
    }

    public Boolean updateNews(Integer id, String header, String content, String post_datetime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("header", header);
        contentValues.put("content", content);
        contentValues.put("post_datetime", post_datetime);
        long result = DB.update("news", contentValues, "id=?", new String[]{id.toString()});
        return result != -1;
    }
}
