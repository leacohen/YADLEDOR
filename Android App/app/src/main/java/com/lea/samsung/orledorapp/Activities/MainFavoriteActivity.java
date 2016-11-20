package com.lea.samsung.orledorapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lea.samsung.orledorapp.*;
import com.lea.samsung.orledorapp.Common.UserContext;

public class MainFavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_favorite_actvity);

        SetHeader();

        SetButtonsNavigation();
    }

    private void SetButtonsNavigation() {
        findViewById(R.id.btnFavoriteSongs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SongsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        findViewById(R.id.btnFavoriteMovies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        findViewById(R.id.btnFavoriteOthers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OtherCategoriesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void OpenActivity(Class classType)
    {

    }

    private void SetHeader() {
        ((TextView)findViewById(R.id.lblMainFavoriteHeader)).setText(
                String.format(
                        getResources().getText(R.string.lblMainFavoriteHeader).toString(),
                        UserContext.getLoggedUser().get_firstName())
        );
    }
}
