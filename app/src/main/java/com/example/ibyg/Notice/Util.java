package com.example.ibyg.Notice;

import android.app.Activity;
import android.widget.Toast;

public class Util {
    private Activity activity;

    public Util(Activity activity){
        this.activity = activity;
    }

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
