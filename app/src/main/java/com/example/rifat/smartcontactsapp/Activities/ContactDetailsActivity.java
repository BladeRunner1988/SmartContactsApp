package com.example.rifat.smartcontactsapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.rifat.smartcontactsapp.Utilities.MyContactsDatabase;
import com.example.rifat.smartcontactsapp.R;

import java.util.Arrays;
import java.util.List;


public class ContactDetailsActivity extends ActionBarActivity implements PopupMenu.OnMenuItemClickListener {

    ListView lvContactNumbersDetails;
    List<String> myContactList;
    private static String selectedNumber;

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

        myContactList = Arrays.asList(MainActivity.clickedContact.getPhoneNumberArray());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myContactList);

        lvContactNumbersDetails.setAdapter(adapter);

        lvContactNumbersDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNumber = myContactList.get(position);
                showPopup(view);
            }
        });

        registerForContextMenu(lvContactNumbersDetails);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.setHeaderTitle("Select");
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int clickedItemID = item.getItemId();
        switch (clickedItemID) {
            case R.id.makeCall:
//                String uri = "tel:" + myContactList.get(position);
//                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
//                startActivity(callIntent);
                break;
            case R.id.sendSMS:
                break;
        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int clickedItemID = item.getItemId();
        switch (clickedItemID) {
            case R.id.makeCall:
                String uri = "tel:" + selectedNumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(uri)));
                break;
            case R.id.sendSMS:
                String number = "12346556";  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
