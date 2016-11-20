package com.lea.samsung.orledorapp.Activities;
import android.os.Bundle;

import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.R;

public class SpessficSubcategoryActivity extends BaseMultimediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        new MultimediaLogic(this).LoadCategoryMultimedias(
                (MultimediaType) extras.getSerializable("Category"),
                extras.getString("Subcategory"));
    }
}
