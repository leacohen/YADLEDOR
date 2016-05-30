package com.lea.samsung.orledorapp;

import android.os.Bundle;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Models.MultimediaType;

public class MovieActivity extends BaseMultimediaActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this.getApplicationContext(),"Movie activity",Toast.LENGTH_LONG).show();

        new MultimediaLogic(this).LoadSpecificMultimedia(MultimediaType.Movie);
    }
}