package com.lea.samsung.orledorapp.Activities;

import android.os.Bundle;

import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Models.MultimediaType;

/**
 * Created by Samsung on 03/07/2016.
 */
public class MemoryGameActivity extends BaseMultimediaActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MultimediaLogic(this).LoadSpecificMultimedia(MultimediaType.MemoryGame);
    }
}
