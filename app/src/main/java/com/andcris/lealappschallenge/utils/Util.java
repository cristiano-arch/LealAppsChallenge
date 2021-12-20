package com.andcris.lealappschallenge.utils;

import android.text.format.DateFormat;

import java.util.Date;

public class Util {

    public static String formatDate(Date date) {
        long millisecond = date.getTime();
        return DateFormat.format("dd/MM/yyyy HH:mm", millisecond).toString();
    }
}
