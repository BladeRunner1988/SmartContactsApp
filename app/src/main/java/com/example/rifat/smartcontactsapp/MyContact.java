package com.example.rifat.smartcontactsapp;

import android.media.Image;

/**
 * Created by Rifat on 3/22/2015.
 */
public class MyContact {
    //Fields
    private String name;
    private String[] phoneNumber;
    private String[] email;
    private String address;
    private String imAlias;
    private String imageLocation;
    private boolean fromPhoneContact;
    private boolean fromFacebookContact;
    private boolean fromGoogleContact;
    private boolean fromTwitterContact;
    private MyNote note;
    //Fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
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
