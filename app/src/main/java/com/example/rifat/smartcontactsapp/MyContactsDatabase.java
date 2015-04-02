package com.example.rifat.smartcontactsapp;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rifat on 3/26/2015.
 */
public class MyContactsDatabase extends SQLiteOpenHelper {

    private String Test = "";
    private String nullColumnHack = null;

    private Context mContext;

//    static fields and methods declared here
    ArrayList<MyContact> myContacts;

//    Declare constant Strings for database creation and other operations here
    private static final int database_VERSION = 1;
    private static final String database_NAME = "ConnectionsDB";
    private static final String database_TABLE = "contact_details";


//    column declaration
    private static final String contact_ID = "_id";
    private static final String contact_NAME = "name";
    private static final String contact_NUMBERS = "numbers";
    private static final String contact_EMAILS = "emails";
    private static final String contact_ADDRESS = "address";
    private static final String contact_IMAGE_LOC = "image";
    private static final String contact_IMAGE_THUMBNAIL_LOC = "image_thumbnail";
    private static final String contact_FROM_PHONE = "phone";
    private static final String contact_FROM_FACEBOOK = "facebook";
    private static final String contact_FROM_GOOGLE = "google";
    private static final String contact_FROM_TWITTER = "twitter";
    private static final String contact_NOTE_TITLE = "note_title";
    private static final String contact_NOTE_BODY= "note_body";
    private static final String contact_NOTE_DATE = "note_date";
    private static final String contact_NOTE_TIME = "note_time";

    //for NullColumnHack


    public MyContactsDatabase(Context context) {
        super(context, database_NAME, null, database_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + database_TABLE
                + " ( " + contact_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + contact_NAME + " TEXT, " + contact_NUMBERS + " TEXT, "
                + contact_EMAILS + " TEXT )";

//        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + database_TABLE
//                + " ( " + contact_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + contact_NAME + " TEXT, " + contact_NUMBERS + " TEXT, "
//                + contact_EMAILS + " TEXT, " + contact_ADDRESS + " TEXT, "
//                + contact_IMAGE_LOC + " TEXT, "
//                + contact_IMAGE_THUMBNAIL_LOC + " TEXT, "
//                + contact_FROM_PHONE + " TEXT, "
//                + contact_FROM_FACEBOOK + " TEXT, "
//                + contact_FROM_GOOGLE + " TEXT, "
//                + contact_FROM_TWITTER + " TEXT, "
//                + contact_NOTE_TITLE + " TEXT, "
//                + contact_NOTE_BODY+ " TEXT, "
//                + contact_NOTE_DATE + " TEXT, "
//                + contact_NOTE_TIME + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + database_TABLE);
        this.onCreate(db);
    }

    public void addNewContact(MyContact myContact) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] contactNumbers = myContact.getPhoneNumber();
        String appendedNumbers = "";
        for(int i=0; i<contactNumbers.length; i++) {
            appendedNumbers += contactNumbers[i] + " ";
        }
        appendedNumbers.trim();

        String[] contactEmails = myContact.getEmail();
        String appendedEmails = "";
        for(int i=0; i<contactEmails.length; i++) {
            appendedEmails += contactEmails[i] + " ";
        }
        appendedEmails.trim();

        ContentValues values = new ContentValues();
        values.put(contact_NAME, myContact.getName());
        values.put(contact_NUMBERS, appendedNumbers);
        values.put(contact_EMAILS, appendedEmails);
//        values.put(contact_ADDRESS, myContact.getAddress());
//        values.put(contact_imAlias, myContact.getImAlias());
//        values.put(contact_IMAGE_LOC, myContact.getImageLocation());
//        values.put(contact_IMAGE_THUMBNAIL_LOC, myContact.getImageThumbnailLocation());
//        if(myContact.isFromPhoneContact()) {
//            values.put(contact_FROM_PHONE, "true");
//        } else if(myContact.isFromGoogleContact()) {
//            values.put(contact_FROM_GOOGLE, "true");
//        } else if(myContact.isFromFacebookContact()) {
//            values.put(contact_FROM_FACEBOOK, "true");
//        } else {
//            values.put(contact_FROM_TWITTER, "true");
//        }
//        values.put(contact_NOTE_TITLE, myContact.getNote().getTitle());
//        values.put(contact_NOTE_BODY, myContact.getNote().getBody());
//        values.put(contact_NOTE_DATE, myContact.getNote().getDate());
//        values.put(contact_NOTE_TIME, myContact.getNote().getTime());
    }

    public void syncAllPhoneContactInAppDB() {
        //get reference of the Connections database
        SQLiteDatabase db = this.getWritableDatabase();

        //get reference of MyContact to save data from ContactsContract
        MyContact contact = new MyContact();

        //
        myContacts = new ArrayList<>();

        //insert values through a ContentValues
        ContentValues values = new ContentValues();

        //Declare necessary variables to access ContactsContract Database
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME_PRIMARY = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        //Get phone contact details from ContactsContract in a Cursor
        ContentResolver contentResolver = mContext.getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        Cursor phoneCursor;
        Cursor emailCursor;

        //populate database with all phone contact information
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, null, null, null);
                emailCursor = contentResolver.query(EmailCONTENT_URI, null, null, null, null);

                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));

                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME_PRIMARY));
                contact.setName(name);
                values.put(contact_NAME, name); //insert name in ContentValues Object -> values

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
                String phoneNumber = "";
                if(hasPhoneNumber > 0) {
                    phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber += phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)) + " ";
                    }
                }
                phoneCursor.close();
                phoneNumber.trim();
                values.put(contact_NUMBERS, phoneNumber); //insert phoneNumbers in ContentValues Object -> values

                String email = "";
                while (emailCursor.moveToNext()) {
                    email += emailCursor.getString(emailCursor.getColumnIndex(DATA)) + " ";
                }
                emailCursor.close();
                email.trim();
                values.put(contact_EMAILS, email); //insert emailIDs in ContentValues Object -> values

                db.insert(database_TABLE, null, values);
            }
        } else {
            Toast.makeText(mContext, "Sorry!!!\nNo Contacts Database Found.", Toast.LENGTH_LONG).show();
        }
        db.close();

//        values.put(contact_ADDRESS, getRelatedContactsAddress());
//        values.put(contact_IMAGE_LOC, "contact image location uri string from ContactsContract");
//        values.put(contact_IMAGE_THUMBNAIL_LOC, "contact thumbnail image location uri string from ContactsContract");
//        values.put(contact_FROM_PHONE, "is this contact info from phone? set true/false");
//        values.put(contact_FROM_FACEBOOK, "is this contact info from Facebook? set true/false");
//        values.put(contact_FROM_GOOGLE, "is this contact info from Google? set true/false");
//        values.put(contact_FROM_TWITTER, "is this contact info from Twitter? set true/false");
//
//        long newRowID = db.insert(database_TABLE, nullColumnHack, values);
    }

    public ArrayList<MyContact> getContactsList() {
        myContacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectionQuery = "SELECET * FROM " + database_TABLE;

        Cursor cursor = db.rawQuery(selectionQuery, null);

        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                MyContact myContact = new MyContact();

                myContact.setName("");
//                myContact.setPhoneNumber(new String[]{});
                myContact.setEmail(cursor.getString(cursor.getColumnIndex(contact_NUMBERS)).split(" "));
//                myContact.setEmail(new String[] {});
                myContact.setEmail(cursor.getString(cursor.getColumnIndex(contact_EMAILS)).split(" "));

                myContacts.add(myContact);
            }
        }
        db.close();

        return myContacts;
    }

    private String getRelatedContactsAddress() {
        return null;
    }

    private String getRelatedContactsNumbers() {

        return null;
    }

    private String getRelatedContactsEmails() {
        return null;
    }
}
