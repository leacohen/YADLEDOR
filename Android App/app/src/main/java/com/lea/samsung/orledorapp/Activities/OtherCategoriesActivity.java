package com.lea.samsung.orledorapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lea.samsung.orledorapp.Inerfaces.ICategoryNotifier;
import com.lea.samsung.orledorapp.Logic.SubcategoriesLogic;
import com.lea.samsung.orledorapp.Models.Category;
import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.R;

import java.util.ArrayList;
import java.util.List;

public class OtherCategoriesActivity extends AppCompatActivity implements ICategoryNotifier {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_categories);

        new SubcategoriesLogic().getCategories(this);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        for (Category cateogry: categories) {
            if(cateogry.getName().equals("Song")) {
                ((ListView)findViewById(R.id.songsList)).setAdapter(
                        new SubcategoriesListAdapter(
                                this,
                                cateogry.getSubcategories(),
                                MultimediaType.Song)
                );
            }

            if(cateogry.getName().equals("Movie")) {
                ((ListView)findViewById(R.id.moviesList)).setAdapter(
                        new SubcategoriesListAdapter(
                                this,
                                cateogry.getSubcategories(),
                                MultimediaType.Movie)
                );
            }

            if(cateogry.getName().equals("Other")) {
                ((ListView)findViewById(R.id.otherList)).setAdapter(
                        new SubcategoriesListAdapter(
                                this,
                                cateogry.getSubcategories(),
                                MultimediaType.Other)
                );
            }
        }
    }
}
