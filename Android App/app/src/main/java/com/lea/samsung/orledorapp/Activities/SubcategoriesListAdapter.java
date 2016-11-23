package com.lea.samsung.orledorapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.R;

import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public class SubcategoriesListAdapter extends BaseAdapter {
    private Activity Activity;
    private MultimediaType mediaType;
    private List<String> Objects;
    private Context Context;

    public SubcategoriesListAdapter(Activity activity, List<String> objects, MultimediaType type) {
        Objects = objects;
        mediaType = type;
        Activity = activity;
        Context = activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return Objects.size();
    }

    @Override
    public String getItem(int position) {
        return Objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private Context getContext()
    {
        return this.Context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) Activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View newView = inflater.inflate(R.layout.simple_list_item1, null);

        ((TextView)newView.findViewById(R.id.categoryName)).setText(getItem(position));

        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Context, SpessficSubcategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Subcategory", getItem(position));
                intent.putExtra("Category", mediaType);
                getContext().startActivity(intent);
            }
        });

        return newView;
    }
}
