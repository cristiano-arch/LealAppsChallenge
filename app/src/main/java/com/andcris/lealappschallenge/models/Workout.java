package com.andcris.lealappschallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Workout implements Parcelable {

    private String name;
    private String description;
    private Date date;

    public Workout() {
    }

    public Workout(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected Workout(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
