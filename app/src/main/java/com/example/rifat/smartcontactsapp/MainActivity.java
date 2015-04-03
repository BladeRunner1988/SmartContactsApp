package com.example.rifat.smartcontactsapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    ListView mContactsListView;
    List<MyContact> myPopulatedContacts;
    CustomContactAdapter customContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mContactsListView = (ListView) findViewById(R.id.myContactList);

        new SyncAllPhoneContactsInAppDB().execute(this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customContactAdapter = new CustomContactAdapter(this, myPopulatedContacts);
        mContactsListView.setAdapter(customContactAdapter);
        mContactsListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        startActivity(intent);
    }

    public class SyncAllPhoneContactsInAppDB extends AsyncTask<Context, Void, Void> {
        @Override
        protected Void doInBackground(Context... params) {
            MyContactsDatabase myContactsDatabase = new MyContactsDatabase(params[0]);
            myPopulatedContacts = myContactsDatabase.syncAllPhoneContactInAppDB();
            return null;
        }
    }

    public void addNewContact() {
        MyContactsDatabase myContactsDatabase = new MyContactsDatabase(this);

        MyContact myContact = new MyContact();

        myContact.setName("");
        //append all phone numbers before passing as parameter
        myContact.setPhoneNumbers("");
        //append all emailIds before passing as parameter
        myContact.setEmailIds("");
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
}
