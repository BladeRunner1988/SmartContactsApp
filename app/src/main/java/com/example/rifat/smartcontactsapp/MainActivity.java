package com.example.rifat.smartcontactsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    ListView mContactsListView;
    public ArrayList<MyContact> myPopulatedContacts = new ArrayList<>();
    CustomContactAdapter customContactAdapter;

    MyContactsDatabase myContactsDatabase = new MyContactsDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mContactsListView = (ListView) findViewById(R.id.myContactList);
        mContactsListView.setOnItemClickListener(this);

//        new SyncAllPhoneContactsInAppDB().execute();
//        new GetContactsListFromAppDB().execute();

        customContactAdapter = new CustomContactAdapter(this, myPopulatedContacts);
        mContactsListView.setAdapter(customContactAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        startActivity(intent);
    }

    public void addNewContact() {
        MyContact myContact = new MyContact();

        myContact.setName("");
        myContact.setPhoneNumber(new String[]{});
        myContact.setEmail(new String[]{});
//        myContact.setAddress("");
//        myContact.setImAlias("");
//        myContact.setImageLocation("");
//        myContact.setThumbnailImageLocation(");
//        if(fromWhere==1) {
//            myContact.setFromPhoneContact(true);
//        } else if (fromWhere==2) {
//            myContact.setFromGoogleContact(true);
//        } else if (fromWhere==3) {
//            myContact.setFromFacebookContact(true);
//        } else {
//            myContact.setFromTwitterContact(true);
//        }
//        myContact.setNote(new MyNote("Note Title", "Note Body", new Date(), new Time()));
        myPopulatedContacts.add(myContact);
        customContactAdapter.notifyDataSetChanged();
        myContactsDatabase.addNewContact(myContact);
    }

    public class SyncAllPhoneContactsInAppDB extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            myContactsDatabase.syncAllPhoneContactInAppDB();
            return null;
        }
    }

    public class GetContactsListFromAppDB extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            myPopulatedContacts = myContactsDatabase.getContactsList();
            return null;
        }
    }
}
