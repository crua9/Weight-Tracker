package com.techreviewsandhelp.weighttracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by crua9 on 4/30/2016.
 */
public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }


    //Back Button
    final Context c = this;
        @Override
        public void onBackPressed(){
       //need to add a save for the settings

        startActivity(new Intent(c, MainActivity.class));
        finish();
    }
}
