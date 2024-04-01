package com.example.lap1;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

public class ContentPrvaider {
    private Activity activity;
    private Context context;
    public  ContentPrvaider(Activity activity) {

        this.activity = activity;
        this.context = activity.getApplicationContext();
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
    public void addContact(String name, String phoneNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        int rawContactID = ops.size(); // Đây là ID nháp để tham chiếu trong các thao tác sau đây

        // Thêm một raw contact mới
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // Thêm tên người dùng vào raw contact
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());

        // Thêm số điện thoại vào raw contact
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        // Áp dụng batch để thực thi tất cả các thao tác thêm mới
        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(context, "Contact added successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to add contact", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteContact(String phoneNumber) {
        String where = ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?";
        String[] params = new String[]{phoneNumber};
        try {
            context.getContentResolver().delete(ContactsContract.Data.CONTENT_URI, where, params);
            context.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, where, params);
            Toast.makeText(context, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to delete contact", Toast.LENGTH_SHORT).show();
        }
    }
}
