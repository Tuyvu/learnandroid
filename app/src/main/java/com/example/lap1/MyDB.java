package com.example.lap1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    public static final String TableName = "ContactTable";
    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String Phone = "Phone";
    public static final String Image = "Image";

    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists "+ TableName + "("
                + Id + " Integer Primary key, "
                + Image + " Text, "
                + Name + " Text, "
                + Phone + " Text)";
        // chạy câu truy vấn SQL để tạo bảng
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang da co
        db.execSQL("Drop table if exists "+ TableName);
        // chay lai
        onCreate(db);
    }
    public  void addContact(Person p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, p.getId());
        values.put(Image, p.getImg());
        values.put(Name, p.getHoTen());
        values.put(Phone, p.getSDT());
        db.insert(TableName, null,values);
        db.close();

    }
    public void updateContact(int id, Person p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, p.getId());
        values.put(Image, p.getImg());
        values.put(Name, p.getHoTen());
        values.put(Phone, p.getSDT());
        db.update(TableName, values,Id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContact (int id) {}
    SQLiteDatabase db = getWritableDatabase();

    public ArrayList<Person> getAllContact(){
        ArrayList<Person> list = new ArrayList<>();
        String sql = "Select * from "  + TableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Person person = new Person(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3));
                list.add(person);
            }
        }
       return list;
    }

}
