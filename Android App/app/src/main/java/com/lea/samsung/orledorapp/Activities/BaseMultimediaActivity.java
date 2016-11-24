package com.lea.samsung.orledorapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lea.samsung.orledorapp.Inerfaces.IRecommendedActivity;
import com.lea.samsung.orledorapp.Inerfaces.IRecommended;
import com.lea.samsung.orledorapp.R;

import java.util.List;

public class BaseMultimediaActivity extends AppCompatActivity implements IRecommendedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimrdia);
    }


    @Override
    public void NothingToShow() {
    }

    @Override
    public void RecommencedListLoaded(List<? extends IRecommended> list) {
        final ListView mLinksList = (ListView) findViewById(R.id.listView);

        MultimediaListAdapter adapter = new MultimediaListAdapter(this, list);

        // Assign adapter to ListView
        mLinksList.setAdapter(adapter);

        // ListView Item Click Listener
        mLinksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                IRecommended itemValue = (IRecommended) mLinksList.getItemAtPosition(position);

                String url = itemValue.get_link();
                if (!url.startsWith("https://") && !url.startsWith("http://")) {
                    url = "http://" + url;
                }

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }
}