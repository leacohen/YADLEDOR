package com.lea.samsung.orledorapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Models.MultimediaType;

public class ClassicMusicActivity extends BaseMultimediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new MultimediaLogic(this).LoadSpecificMultimedia(MultimediaType.ClassicMusic);
    }
}
