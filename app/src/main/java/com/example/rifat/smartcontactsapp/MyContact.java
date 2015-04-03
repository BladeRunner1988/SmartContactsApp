package com.example.rifat.smartcontactsapp;

import java.util.Comparator;

/**
 * Created by Rifat on 3/22/2015.
 */
public class MyContact {

    //Fields
    private String databaseID;
    private String name;
    private String[] phoneNumberArray;
    private String phoneNumbers;
    private String[] emailIdsArray;
    private String emailIds;
    private String address;
    private String imAlias;
    private String imageLocation;
    private boolean fromPhoneContact;
    private boolean fromFacebookContact;
    private boolean fromGoogleContact;
    private boolean fromTwitterContact;
    private MyNote note;
    //Fields

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        this.phoneNumberArray = phoneNumbers.split(" ");
    }

    public String[] getPhoneNumberArray() {
        return phoneNumberArray;
    }

    public String getEmailIds() {
        return emailIds;
    }

    public void setEmailIds(String emailIds) {
        this.emailIds = emailIds;
        this.emailIdsArray = emailIds.split(" ");
    }

    public String[] getEmailIdsArray() {
        return emailIdsArray;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImAlias() {
        return imAlias;
    }

    public void setImAlias(String imAlias) {
        this.imAlias = imAlias;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public boolean isFromPhoneContact() {
        return fromPhoneContact;
    }

    public void setFromPhoneContact(boolean fromPhoneContact) {
        this.fromPhoneContact = fromPhoneContact;
    }

    public boolean isFromFacebookContact() {
        return fromFacebookContact;
    }

    public void setFromFacebookContact(boolean fromFacebookContact) {
        this.fromFacebookContact = fromFacebookContact;
    }

    public boolean isFromGoogleContact() {
        return fromGoogleContact;
    }

    public void setFromGoogleContact(boolean fromGoogleContact) {
        this.fromGoogleContact = fromGoogleContact;
    }

    public boolean isFromTwitterContact() {
        return fromTwitterContact;
    }

    public void setFromTwitterContact(boolean fromTwitterContact) {
        this.fromTwitterContact = fromTwitterContact;
    }

    public MyNote getNote() {
        return note;
    }

    public void setNote(MyNote note) {
        this.note = note;
    }
}
