package com.lea.samsung.orledorapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lea.samsung.orledorapp.Logic.FavoriteLogic;

public class FavoriteActivity extends BaseMultimediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FavoriteLogic().LoadFavoriteMedia(this);
    }
}
