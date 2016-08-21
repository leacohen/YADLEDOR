package com.lea.samsung.orledorapp;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.lea.samsung.orledorapp.Activities.MemoActivity;
import com.lea.samsung.orledorapp.Activities.MovieActivity;
import com.lea.samsung.orledorapp.Activities.SongsActivity;
import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.Inerfaces.IRecommended;
import com.lea.samsung.orledorapp.Inerfaces.IRecommendedActivity;
import com.lea.samsung.orledorapp.Logic.FavoriteLogic;
import com.lea.samsung.orledorapp.Logic.MultimediaLogic;
import com.lea.samsung.orledorapp.Activities.Login.LoginActivity;
import com.lea.samsung.orledorapp.Models.BaseMultimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IRecommendedActivity {

    private ListView mLinksList;
    private String m_TextName = "";
    private String m_TextURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // If the user is not logged in, switch to login page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if(UserContext.getLoggedUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        if(UserContext.getLoggedUser() != null) {
            ((TextView) findViewById(R.id.textView5)).setText("שלום " + UserContext.getLoggedUser().get_firstName());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_TextName = "";
                showDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RefreshMultimediaList();
    }

    private void RefreshMultimediaList()
    {
        new MultimediaLogic(this).LoadAllMultimedia();
    }
    public void NothingToShow() {
        // TODO: change the message..
        Toast.makeText(this.getApplicationContext(),"-- no songs -- ", Toast.LENGTH_LONG).show();
    }

    public void RecommencedListLoaded(List<IRecommended> songList)
    {
        mLinksList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<IRecommended> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                songList);

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
                if(!url.startsWith("https://") && !url.startsWith("http://")) {
                    url = "http://" + url;
                }

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    //function that get the link and url from the client and set it in the DB
    private void showDialog() {

        final Dialog dialog = new Dialog(this);


        dialog.setContentView(R.layout.dialog_add_new_link);

        final EditText inputName = (EditText)dialog.findViewById(R.id.input_name);
        final EditText inputURL = (EditText)dialog.findViewById(R.id.input_link);
        final Spinner inputType = (Spinner)dialog.findViewById(R.id.multimedia_spinner);

        Button saveBtn = (Button)dialog.findViewById(R.id.dialog_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_TextName = inputName.getText().toString();
                m_TextURL = inputURL.getText().toString();
                MultimediaType type = MultimediaType.values()[inputType.getSelectedItemPosition()];

                BaseMultimedia b = new BaseMultimedia();
                b.set_name(m_TextName);
                b.set_link(m_TextURL);
                b.set_type(type);

                new FavoriteLogic().AddLink(b);
                dialog.cancel();
                RefreshMultimediaList();
            }
        });
        Button cancelBtn = (Button)dialog.findViewById(R.id.dialog_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_notification) {
            Intent intent = new Intent(this, MemoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this, SongsActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this,MovieActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
