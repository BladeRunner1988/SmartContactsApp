package com.example.rifat.smartcontactsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView mContactsListView;
    EditText etInputSearch;
    static List<MyContact> myPopulatedContacts;
    CustomContactAdapter customContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        new SyncAllPhoneContactsInAppDB().execute(this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final List<MyContact> sortedContacts = new ArrayList<>(myPopulatedContacts);
        Collections.sort(sortedContacts, new ContactNameSorter());

        mContactsListView = (ListView) findViewById(R.id.myContactList);
        etInputSearch = (EditText) findViewById(R.id.etInputSearch);

        customContactAdapter = new CustomContactAdapter(getApplicationContext(), sortedContacts);
        mContactsListView.setAdapter(customContactAdapter);

        etInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textlength = s.length();
                List<MyContact> tempArrayList = new ArrayList<>();
                for(MyContact contact: sortedContacts){
                    if (textlength <= contact.getName().length()) {
                        if (contact.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            tempArrayList.add(contact);
                        }
                    }
                }
                customContactAdapter = new CustomContactAdapter(getApplicationContext(), tempArrayList);
                mContactsListView.setAdapter(customContactAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        customContactAdapter = new CustomContactAdapter(this, R.id.etInputSearch, sortedContacts);
//        mContactsListView.setAdapter(customContactAdapter);
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
