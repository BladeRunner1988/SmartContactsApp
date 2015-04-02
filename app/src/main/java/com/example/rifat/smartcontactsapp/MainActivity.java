package com.example.rifat.smartcontactsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


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
