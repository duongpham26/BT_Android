package com.duongpham26.bt6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "student_db";
    private static final int DB_VERSION = 1;

    public StudentDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "mssv TEXT," +
                "avatar BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }

    public void insertStudent(Student s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", s.getName());
        values.put("mssv", s.getMssv());
        values.put("avatar", s.getAvatar());
        db.insert("Student", null, values);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Student", null);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String mssv = c.getString(2);
                byte[] avatar = c.getBlob(3);
                list.add(new Student(id, name, mssv, avatar));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
}




