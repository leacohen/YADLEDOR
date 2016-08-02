package com.lea.samsung.orledorapp.Activities;

import android.os.Bundle;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Models.MultimediaType;

public class SongsActivity extends BaseMultimediaActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MultimediaLogic(this).LoadSpecificMultimedia(MultimediaType.Song);
    }
}