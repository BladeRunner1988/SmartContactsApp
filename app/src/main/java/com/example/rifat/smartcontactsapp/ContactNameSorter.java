package com.example.rifat.smartcontactsapp;

import java.util.Comparator;

/**
 * Created by Rifat on 4/3/2015.
 */
public class ContactNameSorter implements Comparator<MyContact> {
    @Override
    public int compare(MyContact aContact, MyContact bContact) {
        return aContact.getName().compareToIgnoreCase(bContact.getName());
    }
}
