package com.monta.tp6_gestion_etudent.connxbd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.monta.tp6_gestion_etudent.Model.Student;

import java.util.ArrayList;


public class studentOpenHelper extends SQLiteOpenHelper {
    //database version
    private static final int DATA_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "studentDB";

    //tabel name
    private static final String table_NAME = "student";

    //table colums names
    private static final String ID = "ID";
    private static final String Name = "Name";
    private static final String Note = "Note";


    public studentOpenHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + table_NAME + "(" + ID + " Integer PRIMARY KEY AUTOINCREMENT," + Name + " TEXT," + Note+ " Number(2,2)" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + table_NAME);
        onCreate(db);

    }

    //add new Student
    public void addStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, s.getName());
        values.put(Note, s.getNote());
        db.insert(table_NAME, null, values);
        db.close();


    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> StudentList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + table_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do{

            Student std = new Student();
            std.setId(Integer.parseInt((cursor.getString(0))));
            std.setName(cursor.getString(1));
            std.setNote(Double.parseDouble(cursor.getString(2)));

            StudentList.add(std);
        }  while (cursor.moveToNext()) ;

        }
        return StudentList;
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public  Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_NAME, new String[]{ID, Name, Note}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

        }
        Student std = new Student(cursor.getString(1), Double.parseDouble(cursor.getString(2)));
        //return student
        return std;

    }

    public int updateStudent(Student std) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, std.getId());
        values.put(Name, std.getName());
        values.put(Name, std.getNote());
// updating row
        return db.update(table_NAME, values, ID + " = ?",
                new String[]{String.valueOf(std.getId())});
    }


}
