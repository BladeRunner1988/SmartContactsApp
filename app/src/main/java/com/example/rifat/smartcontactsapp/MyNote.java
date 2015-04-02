package com.example.rifat.smartcontactsapp;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by Rifat on 3/30/2015.
 */
public class MyNote {
    private String title;
    private String body;
    private Date date;
    private Time time;

    public MyNote(String title, String body, Date date, Time time) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
