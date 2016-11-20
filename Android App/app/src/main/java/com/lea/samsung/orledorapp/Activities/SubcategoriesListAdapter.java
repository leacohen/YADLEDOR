package com.lea.samsung.orledorapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.R;

import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public class SubcategoriesListAdapter extends ArrayAdapter<String> {
    private MultimediaType mediaType;

    public SubcategoriesListAdapter(Context context, List<String> objects, MultimediaType type) {
        super(context, R.layout.simple_list_item1, objects);
        mediaType = type;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View newView = super.getView(position, convertView, parent);

        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SpessficSubcategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Subcategory", getItem(position));
                intent.putExtra("Category", mediaType);
                getContext().startActivity(intent);
            }
        });

        return newView;
    }
}
