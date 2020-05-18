package com.bawp.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bawp.contactmanager.R;
import com.bawp.contactmanager.model.Contact;
import com.bawp.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;
//CLASS THAT HANDLES EVERYTHING THAT A DATABASE DOES
//CRUD : CREATE READ UPDATE DELETE

//Extending SQLiteOpenHelper
public class DatabaseHandler extends SQLiteOpenHelper {

    //Where we passing the database name and version initialized in the Util Class
    public DatabaseHandler(Context context ) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //Where we create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language
        /*
           create table _name(id, name, phone_number);
         */
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                   + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                   + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        //creating our table
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again
        onCreate(db);
        

    }

    /*
       CRUD = Create, Read, Update, Delete

     */

    //Add Contact (create)
    public void addContact(Contact contact) {
        //Initializing our database
         SQLiteDatabase db = this.getWritableDatabase();

         //ContentValues is a data structure used for database
        //Initialize as value
        ContentValues values = new ContentValues();

        //NO NEED TO ADD THE ID SINCE SQL SYSTEM WILL TAKE CARE OF IF AUTOMATICALLY
        //Getting the name of contact
        values.put(Util.KEY_NAME, contact.getName());
        //Getting the phone number of contact
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert to row
        //Passing table_name, nullColumnHack (always null), and value to insert
        db.insert(Util.TABLE_NAME, null, values);

        //Log.d("DBHandler", "addContact: " + "item added");


        //closing db connection!
        //Really good to avoid the memory leaks
        db.close();



    }

    //Get a contact
    //Passing an id that will identify the specific contact we need
    public Contact getContact(int id) {
        //Using getReadableDatabase to access data in the current db database
        SQLiteDatabase db = this.getReadableDatabase();
        String[]  columns =  new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER};
        //Cursing is allowing us to iterate thru our database
        //Passing the table name , the values we want to get, from which value,
        //groupBy, having, orderBy
        Cursor cursor = db.query(Util.TABLE_NAME, columns ,
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null, null, null);

        //Moving cursor to the first row of  our table
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact("James", "213986");
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    //Get all Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    //Update contact
    //Return the id that we are updating
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update the row
        //update(tablename, values, where id = 43)
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Delete single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                 new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    //Get contacts count
    //Getting the numbers of rows in our database
    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }
}
