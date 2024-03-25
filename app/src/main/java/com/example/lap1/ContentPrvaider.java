package com.example.lap1;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ContentPrvaider {
    private Activity activity;
    public  ContentPrvaider(Activity activity) {
        this.activity = activity;
    }
    public ArrayList<Person> getAllContact() {
        ArrayList<Person> listContact = new ArrayList<>();
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
        };
        Cursor cursor = activity.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, null, null,null
        );
        if(cursor.moveToFirst()){
            do {
                Person p = new Person(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(2));
                listContact.add(p);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return listContact;
    }

}
