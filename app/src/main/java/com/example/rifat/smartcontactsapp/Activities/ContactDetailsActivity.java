package com.example.rifat.smartcontactsapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.rifat.smartcontactsapp.Utilities.MyContactsDatabase;
import com.example.rifat.smartcontactsapp.R;

import java.util.Arrays;
import java.util.List;


public class ContactDetailsActivity extends ActionBarActivity implements PopupMenu.OnMenuItemClickListener {

    ListView lvContactNumbersDetails;
    ListView lvEmailsDetails;
    List<String> myContactList;
    List<String> myEmailIDList;

    private static String selectedNumber;
    private static String selectedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        init();

        MyContactsDatabase myContactsDatabase = new MyContactsDatabase(this);
        String allData = myContactsDatabase.test();

    }

    private void init() {
        lvContactNumbersDetails = (ListView) findViewById(R.id.lvContactNumbersDetails);
        lvEmailsDetails = (ListView) findViewById(R.id.lvEmailsDetails);

        myContactList = Arrays.asList(MainActivity.clickedContact.getPhoneNumberArray());
        myEmailIDList = Arrays.asList(MainActivity.clickedContact.getEmailIdsArray());
        ArrayAdapter<String> numbersAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myContactList);

        ArrayAdapter<String> emailsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myContactList);

        lvContactNumbersDetails.setAdapter(numbersAdapter);
        lvEmailsDetails.setAdapter(emailsAdapter);

        lvContactNumbersDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNumber = myContactList.get(position);
                showCallSMSPopup(view);
            }
        });

        lvEmailsDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEmail = myContactList.get(position);
                showEmailPopUp(view);
            }
        });

        registerForContextMenu(lvContactNumbersDetails);
        registerForContextMenu(lvEmailsDetails);
    }

    public void showCallSMSPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up_menu, popup.getMenu());
        popup.show();
    }

    public void showEmailPopUp(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up_email_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int clickedItemID = item.getItemId();
        switch (clickedItemID) {
            case R.id.makeCall:
                makeCall();
                break;
            case R.id.sendSMS:
                sendSMS();
                break;
            case R.id.sendEmail:
                sendEmail();
                break;
        }
        return true;
    }

    private void makeCall() {
        String uri = "tel:" + selectedNumber;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(uri)));
    }

    private void sendSMS() {
        String number = selectedNumber;  // The number on which you want to send SMS
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
    }

    private void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, selectedEmail);
        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
